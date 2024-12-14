package com.rag.rag4j.embadding.application.domain.service;

import com.rag.rag4j.common.documents.UseCase;
import com.rag.rag4j.embadding.application.port.out.EmbeddingPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class EmbeddingService {

    private final EmbeddingPort embeddingPort;

    public Mono<double[]> generateEmbedding(String text) {
        return embeddingPort.getEmbedding(text);
    }
}
