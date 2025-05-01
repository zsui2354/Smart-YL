//package org.hae.yl.common.config;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.SimpleClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
//
//@Configuration
//public class RestClientConfig {
//    @Bean
//    public RestTemplate restTemplate() {
//        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//        factory.setConnectTimeout(5000);   // 5秒连接超时
//        factory.setReadTimeout(120_000);    // 2分钟读取超时（适合长流）
//        return new RestTemplate(factory);
//    }
//
//    @Bean
//    public ObjectMapper objectMapper() {
//        return new ObjectMapper()
//                .registerModule(new JavaTimeModule())
//                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//    }
//}
