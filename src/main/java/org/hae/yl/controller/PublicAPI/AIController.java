package org.hae.yl.controller.PublicAPI;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/ai")
public class AIController {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:11434")
            .build();


    @PostMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamAIResponse(@RequestBody Map<String, Object> payload) {

        System.out.println("开始执行AI 对话");
        // 强制添加参数 stream=true
        payload.put("stream", true);

        return webClient.post()
                .uri("/api/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(payload)
                .accept(MediaType.APPLICATION_NDJSON, MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(String.class) // 逐行接收JSON字符串
                .delayElements(Duration.ofMillis(10)); // 控制速度防止前端处理不过来
    }



















    @GetMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter chat(@RequestParam String prompt) {
        System.out.println("开始执行SSE");
        SseEmitter emitter = new SseEmitter();
        new Thread(() -> {
            try {
                // 模拟响应流
                emitter.send(SseEmitter.event().data("你问的是: " + prompt));
                emitter.send(SseEmitter.event().data("这是第二段回答..."));
                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        }).start();
        return emitter;
    }
}
