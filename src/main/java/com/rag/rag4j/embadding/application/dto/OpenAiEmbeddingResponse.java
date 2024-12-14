package com.rag.rag4j.embadding.application.dto;

import java.util.List;

public record OpenAiEmbeddingResponse (List<EmbeddingData> data) {

    public record EmbeddingData(List<Double> embedding) {
    }
}
