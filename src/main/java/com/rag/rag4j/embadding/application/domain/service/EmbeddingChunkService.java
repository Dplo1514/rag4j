package com.rag.rag4j.embadding.application.domain.service;

import com.rag.rag4j.common.documents.UseCase;
import com.rag.rag4j.embadding.application.port.in.EmbeddingChunkUseCase;
import com.rag.rag4j.embadding.application.port.out.ChromaPort;
import com.rag.rag4j.embadding.application.port.out.EmbeddingPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@UseCase
@RequiredArgsConstructor
public class EmbeddingChunkService implements EmbeddingChunkUseCase {

    private final EmbeddingPort embeddingPort; // OpenAI 임베딩 서비스
    private final ChromaPort chromaPort;       // ChromaDB 저장 서비스

    @Override
    public Mono<Void> embedAndStore(String collectionId, String documentId, String text) {
        return embeddingPort.getEmbedding(text) // 1. 임베딩 생성
                .flatMap(embedding -> {
                    // 2. ChromaDB에 저장
                    chromaPort.saveEmbedding(
                            collectionId,
                            List.of(embedding),                  // 임베딩 리스트
                            List.of(documentId),                 // 문서 ID 리스트
                            List.of(text),                       // 문서 텍스트 리스트
                            List.of(Map.of("source", "API"))    // 메타데이터 리스트
                    );
                    return Mono.empty(); // 완료 시 반환
                });
    }
}