package org.hae.yl.controller.PublicAPI;

//import cn.hutool.json.ObjectMapper;
import lombok.Value;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hae.yl.model.PromptRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.http.*;
import reactor.core.publisher.Flux;


import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class DeepSeekController {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Async
    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody Map<String, String> body) {
        String prompt = body.get("KEY");

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:11434/api/generate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> request = new HashMap<>();
        request.put("model", "deepseek-r1:8b");
        request.put("prompt", prompt);
        request.put("stream", false);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        return ResponseEntity.ok(response.getBody());
    }


    @PostMapping("/chat/stream")
    public ResponseBodyEmitter chatStream(@RequestBody Map<String, String> body) {
        String prompt = body.get("prompt");

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:11434")
                .build();

        Map<String, Object> request = new HashMap<>();
        request.put("model", "deepseek-r1:8b");
        request.put("prompt", prompt);
        request.put("stream", true);

        ResponseBodyEmitter emitter = new ResponseBodyEmitter();

        webClient.post()
                .uri("/api/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .accept(MediaType.TEXT_EVENT_STREAM, MediaType.APPLICATION_NDJSON, MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(String.class)
                .doOnNext(data -> {
                    try {
                        emitter.send(data);
                    } catch (Exception e) {
                        emitter.completeWithError(e);
                    }
                })
                .doOnError(emitter::completeWithError)
                .doOnComplete(emitter::complete)
                .subscribe();

        return emitter;
    }


    @PostMapping("/chatDS")
    public SseEmitter chatDS(@RequestBody Map<String, String> body) {
        String prompt = body.get("KEY");

        SseEmitter emitter = new SseEmitter();
        new Thread(() -> {
            try {
                // 发送请求到 DeepSeek 进行流式响应
                RestTemplate restTemplate = new RestTemplate();
                String url = "http://localhost:11434/api/generate";

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                Map<String, Object> request = new HashMap<>();
                request.put("model", "deepseek-r1:8b");
                request.put("prompt", prompt);
                request.put("stream", true);

                HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

                // 获取流式响应
                ResponseEntity<String> response = restTemplate.exchange(
                        url, HttpMethod.POST, entity, String.class);

                // 模拟分块响应
                String[] chunks = response.getBody().split("\n");

                for (String chunk : chunks) {
                    // 每个块发送一个 SSE 事件
                    emitter.send(SseEmitter.event().data(chunk));
                }

                emitter.complete();  // 结束流式数据
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        }).start();

        return emitter;  // 返回 SseEmitter，前端可以通过 EventSource 接收
    }


    @GetMapping(value = "/stream", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public ResponseBodyEmitter stream() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Map<String, String> chunk = new HashMap<>();
                    chunk.put("response", "第 " + i + " 条回复");
                    emitter.send(chunk, MediaType.APPLICATION_JSON);

                    Thread.sleep(1000); // 模拟逐条推送
                }
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }


    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> sse() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> "data: 第 " + seq + " 条消息\n\n")
                .take(10);
    }
}



//    @ServerEndpoint("/ws")
//    public class WebSocketServer {
//        @OnOpen
//        public void onOpen(Session session) {
//            sessions.add(session);
//        }
//
//        @OnMessage
//        public void onMessage(String message, Session session) {
//            // 收到前端消息后，实时推送回去
//            session.getAsyncRemote().sendText("回应: " + message);
//        }
//    }





//    @GetMapping(value = "/stream", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseBodyEmitter streamG() {
//        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//
//        executor.submit(() -> {
//            try {
//                // 模拟流式响应
//                Map<String, Object> response = new HashMap<>();
//                response.put("model", "deepseek-r1:8b");
//                response.put("created_at", "2025-04-28T02:39:44.141624Z");
//                response.put("response", "<think>");
//                response.put("done", false);
//
//                emitter.send(response); // 第一个数据
//
//                // 模拟延迟，模拟多次流式推送
//                Thread.sleep(1000);
//
//                response.put("done", true); // 完成响应
//                emitter.send(response);
//
//                emitter.complete();
//            } catch (Exception e) {
//                emitter.completeWithError(e);
//            }
//        });
//
//        return emitter;
//    }





//    @Async
//    @PostMapping("/streamchat")
//    public ResponseEntity<StreamingResponseBody> chat2(@RequestBody Map<String, String> body) {
//        String prompt = body.get("prompt");
//
//        // 日志：查看请求体
//        System.out.println("Request Body: " + body);
//
//        // 创建 CloseableHttpClient 实例
//        CloseableHttpClient httpClient = HttpClients.custom().build();
//        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
//        String url = "http://localhost:11434/api/generate";
//
//        // 请求头设置
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        // 请求体构建
//        Map<String, Object> request = new HashMap<>();
//        request.put("model", "deepseek-r1:8b");
//        request.put("prompt", prompt);
//        request.put("stream", true);  // 开启流式响应
//
//        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
//
//        // 使用 StreamingResponseBody 进行流式返回
//        return ResponseEntity.ok()
//                .contentType(MediaType.TEXT_PLAIN)
//                .body(outputStream -> {
//                    // 执行请求，获取响应流
//                    restTemplate.execute(url, HttpMethod.POST, request1 -> {
//                        // 设置请求头和请求体
//                        request1.getHeaders().addAll(headers);
//                        String jsonRequestBody = new ObjectMapper().writeValueAsString(request);
//                        request1.getBody().write(jsonRequestBody.getBytes());
//                    }, response -> {
//                        try (InputStream inputStream = response.getBody()) {
//                            byte[] buffer = new byte[1024];
//                            int len;
//                            while ((len = inputStream.read(buffer)) != -1) {
//                                // 打印接收到的数据块
//                                System.out.println("Sending data chunk: " + new String(buffer, 0, len));  // 打印读取到的内容
//                                outputStream.write(buffer, 0, len);
//                                outputStream.flush();  // 立即推送给客户端
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        // 这里不需要关闭 outputStream，由 Spring 自动管理
//                    });
//                });
//    }
//





//    private RequestCallback requestCallback(HttpEntity<Map<String, Object>> entity, HttpHeaders headers) {
//        return request -> {
//            // 设置请求头和请求体
//            request.getHeaders().addAll(headers);
//            ObjectMapper objectMapper = new ObjectMapper();
//            String jsonBody = objectMapper.writeValueAsString(entity.getBody());
//            request.getBody().write(jsonBody.getBytes());
//        };
//    }
//
//    private ResponseExtractor<Void> responseExtractor(OutputStream outputStream) {
//        return response -> {
//            try (InputStream inputStream = response.getBody()) {
//                byte[] buffer = new byte[1024];
//                int len;
//                while ((len = inputStream.read(buffer)) != -1) {
//                    outputStream.write(buffer, 0, len);
//                    outputStream.flush();
//                }
//            }
//            return null;
//        };
////    }
//}
