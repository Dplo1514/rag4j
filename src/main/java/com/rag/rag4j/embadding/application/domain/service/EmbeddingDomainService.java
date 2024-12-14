package com.rag.rag4j.embadding.application.domain.service;

import java.util.List;

public class EmbeddingDomainService {

    public double calculateCosineSimilarity(List<Double> vector1, List<Double> vector2) {
        if (vector1.size() != vector2.size()) {
            throw new IllegalArgumentException("벡터의 크기가 동일해야 합니다.");
        }

        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < vector1.size(); i++) {
            dotProduct += vector1.get(i) * vector2.get(i);
            normA += Math.pow(vector1.get(i), 2);
            normB += Math.pow(vector2.get(i), 2);
        }

        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
