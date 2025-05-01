package org.hae.yl.controller.PublicAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequestMapping("/api")
public class StreamDeepSeekController {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public StreamDeepSeekController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Async
    @PostMapping(value = "/stream-chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<StreamingResponseBody> streamChat(@RequestBody Map<String, String> requestBody) {
        String prompt = requestBody.get("prompt");
        String apiUrl = "http://localhost:11434/api/generate";

        // 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 构造请求参数
        Map<String, Object> requestParams = new ConcurrentHashMap<>();
        requestParams.put("model", "deepseek-r1:8b");
        requestParams.put("prompt", prompt);
        requestParams.put("stream", true); // 必须启用流式模式
        requestParams.put("max_tokens", 2048); // 控制最大生成长度

        try {
            // 执行流式请求
            InputStream apiResponseStream = restTemplate.execute(
                    apiUrl,
                    HttpMethod.POST,
                    request -> {
                        request.getHeaders().addAll(headers);
                        objectMapper.writeValue(request.getBody(), requestParams);
                    },
                    response -> {
                        if (response.getStatusCode().is2xxSuccessful()) {
                            return response.getBody();
                        }
                        throw new RuntimeException("API请求失败: " + response.getStatusCode());
                    }
            );

            // 构建流式响应体
            StreamingResponseBody responseBody = outputStream -> {
                byte[] buffer = new byte[1024];
                int bytesRead;
                try (InputStream inputStream = apiResponseStream) {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        outputStream.flush();
                    }
                } catch (IOException e) {
                    log.error("流传输中断: {}", e.getMessage());
                }
            };

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_EVENT_STREAM_VALUE)
                    .body(responseBody);

        } catch (Exception e) {
            log.error("流式请求失败 [Prompt: {}]: {}", prompt, e.getMessage(), e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "流式通信失败: " + e.getMessage(),
                    e
            );
        }
    }
}
