package com.rag.rag4j.prompt.application.domain.model;

import lombok.Getter;

// 템플릿 도메인 모델
@Getter
public record Template(String inputText, String similarChunks, String prompt) {

}
