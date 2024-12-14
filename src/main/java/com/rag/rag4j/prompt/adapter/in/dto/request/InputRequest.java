package com.rag.rag4j.prompt.adapter.in.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 사용자 입력을 캡슐화하는 요청 DTO
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InputRequest {
    // 사용자 입력 텍스트
    private String inputText;
}