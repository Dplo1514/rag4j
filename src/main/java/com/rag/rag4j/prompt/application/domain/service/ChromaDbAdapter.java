package com.rag.rag4j.prompt.application.domain.service;

import com.rag.rag4j.common.documents.port.PersistenceOutputPort;
import com.rag.rag4j.prompt.application.port.out.ChromaDbPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@PersistenceOutputPort
@RequiredArgsConstructor
public class ChromaDbAdapter implements ChromaDbPort {

    private final WebClient webClient;

    public String getSimilarChunks(String inputText,String keyWord){
        String payload = String.format("{\"query\": \"%s\", \"keyword\": \"%s\"}", inputText, keyWord);
        try {
            // WebClient를 이용해 REST API 호출
            return webClient.post()
                    .uri("/search")  // TODO : Chroma DB 엔드포인트
                    .header("Content-Type", "application/json")
                    .bodyValue(payload)
                    .retrieve()
                    .bodyToMono(String.class)  // 결과를 문자열로 변환
                    .block();  // 동기식 호출
        } catch (WebClientResponseException e) {
            // API 호출 오류 처리
            throw new RuntimeException("Error querying Chroma DB: " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            // 기타 예외 처리
            throw new RuntimeException("Unexpected error: " + e.getMessage(), e);
        }
    }
}
