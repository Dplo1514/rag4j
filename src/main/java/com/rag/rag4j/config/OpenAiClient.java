package com.rag.rag4j.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class OpenAiClient {

    private final OpenAiProperties openAiProperties; // 설정 정보 주입
    private final RestTemplate restTemplate;

    public OpenAiClient(OpenAiProperties openAiProperties, RestTemplate restTemplate) {
        this.openAiProperties = openAiProperties;
        this.restTemplate = restTemplate;
    }

    /**
     * OpenAI API 호출
     * @param prompt OpenAI에 전달할 프롬프트 (사용자의 질문쿼리와 인덱스종류 이넘이 파라미터로 주어지고 → 오픈 AI에게 사용자 질문이 어떤 enum키워드와 가장 적절한지 응답)
     * @return OpenAI의 응답 텍스트
     */
    public String query(String prompt) {
        try {
            // 1. HTTP 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + openAiProperties.getApiKey());
            headers.set("Content-Type", "application/json");

            // 2. JSON 요청 본문 생성
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode requestBody = objectMapper.createObjectNode();
            requestBody.put("model", openAiProperties.getModel()); // 모델 설정
            requestBody.put("max_tokens", 100); // 응답 최대 토큰 수
            requestBody.put("temperature", 0.5); // 응답 다양성 조정
            ObjectNode message = objectMapper.createObjectNode();
            message.put("role", "user");
            message.put("content", prompt);
            requestBody.putArray("messages").add(message);

            // 3. HTTP 요청 생성
            HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);

            // 4. OpenAI API 호출
            ResponseEntity<JsonNode> response = restTemplate.exchange(
                    openAiProperties.getBaseUrl() + "chat/completions",
                    HttpMethod.POST,
                    entity,
                    JsonNode.class
            );

            // 5. 응답 처리
            JsonNode responseBody = response.getBody();
            if (responseBody != null) {
                return responseBody.get("choices").get(0).get("message").get("content").asText().trim();
            } else {
                throw new RuntimeException("OpenAI 응답이 비어 있습니다.");
            }
        } catch (Exception e) {
            throw new RuntimeException("OpenAI API 호출 실패: " + e.getMessage(), e);
        }
    }


}



