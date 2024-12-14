package com.rag.rag4j.prompt.application.domain.service;

import com.rag.rag4j.common.documents.UseCase;

@UseCase
public class PromptGenerateService {
    public String generate() {

        StringBuilder prompt = new StringBuilder();

        // 카테고리 정보를 포함한 미션 생성 요청
        prompt.append("\n 다음 규칙을 반드시 지키도록 하시오.\n" +
            "1. 결과 답변은 반드시 텍스트와 수치를 기반으로 기술하시오\n" +
            "2, 사실에 기반한 내용을 분명하고 간결하게 기술하시오\n" +
            "3, 문장 이 외에 구조는 금지하며 내용에 기반한 사실만 발언하시오\n" +
            "4, 하나의 카테고리에 중복된 단어를 사용한 미션은 금지.\n");

        return prompt.toString();
    }

}
