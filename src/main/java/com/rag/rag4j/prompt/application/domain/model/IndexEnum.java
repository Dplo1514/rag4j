package com.rag.rag4j.prompt.application.domain.model;

import lombok.Getter;

@Getter
public enum IndexEnum {
    LAW("법"),
    LEGAL("법률"),
    ORDER("질서");

    // 키워드 반환
    private final String keyword;

    IndexEnum(String keyword) {
        this.keyword = keyword; // Enum에 키워드 할당
    }

    @Override
    public String toString() {
        return keyword; // 키워드 문자열 반환
    }
}
