package com.rag.rag4j.embadding.adapter.out;

import com.rag.rag4j.embadding.application.dto.OpenAiEmbeddingResponse;
import com.rag.rag4j.embadding.application.port.out.EmbeddingPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
public class OpenAiEmbeddingClient implements EmbeddingPort {

    @Value("${openai.api.url}")
    private String openaiApiUrl;

    @Value("${openai.api.key}")
    private String apiKey;

    private final WebClient webClient;

    public OpenAiEmbeddingClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public Mono<double[]> getEmbedding(String text) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "text-embedding-ada-002");
        requestBody.put("input", text);

        return webClient.post()
                .uri(openaiApiUrl)
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(OpenAiEmbeddingResponse.class)
                .timeout(Duration.ofSeconds(60))
                .map(response -> response.data().get(0).embedding().stream()
                        .mapToDouble(Double::doubleValue).toArray());
    }
}
