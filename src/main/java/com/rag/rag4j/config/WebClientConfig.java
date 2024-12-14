package com.rag.rag4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

// 이승현 : DB 연결을 위한 컨픽 추가
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8000")  // TODO : Chroma DB 서버 주소
                .build();
    }
}
