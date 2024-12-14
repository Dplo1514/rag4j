package com.rag.rag4j.embadding;

import com.rag.rag4j.embadding.application.domain.service.EmbeddingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EmbeddingTest {

    @Autowired
    private EmbeddingService embeddingService;

    @Test
    public void testEmbedding() {
        // Given: 테스트할 텍스트 정의
        String text = "안녕하세요";

        // When: EmbeddingService를 호출하여 임베딩 결과를 가져옴
        Mono<double[]> resultMono = embeddingService.generateEmbedding(text);
        double[] vector = resultMono.block();  // Mono를 동기적으로 변환

        // Then: 결과 검증
        assertNotNull(vector); // 벡터가 null이 아님을 확인
        assertTrue(vector.length > 0); // 벡터의 크기가 0보다 큼을 확인

        // 출력 (테스트 목적으로 출력)
        int cnt = 0;
        for (double v : vector) {
            cnt++;
            System.out.println("v = " + v);
        }
        System.out.println(cnt);
    }
}
