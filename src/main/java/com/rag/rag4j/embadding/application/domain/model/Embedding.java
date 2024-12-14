package com.rag.rag4j.embadding.application.domain.model;

public class Embedding {

    private final String text;
    private final double[] vector;

    // 생성자
    public Embedding(String text, double[] vector) {
        this.text = text;
        this.vector = vector;
    }

    // Getter 메서드
    public String getText() {
        return text;
    }

    public double[] getVector() {
        return vector;
    }
}
