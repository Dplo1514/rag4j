package com.rag.rag4j.embadding.application.port.in;

import reactor.core.publisher.Mono;

public interface EmbeddingChunkUseCase {

    Mono<Void> embedAndStore(String collectionId, String documentId, String text);
}
