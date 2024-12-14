package com.rag.rag4j.embadding.application.port.out;

import reactor.core.publisher.Mono;

public interface EmbeddingPort {

    Mono<double[]> getEmbedding(String text);
}
