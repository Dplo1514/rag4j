package com.rag.rag4j.prompt.application.domain.service;

import com.rag.rag4j.common.documents.UseCase;
import com.rag.rag4j.config.OpenAiClient;
import com.rag.rag4j.prompt.application.domain.model.IndexEnum;
import com.rag.rag4j.template.application.port.in.TempUseCase;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@UseCase
@Service
public class OpenAIService implements TempUseCase {

    private final OpenAiClient openAiClient;

    public OpenAIService(OpenAiClient openAiClient) {
        this.openAiClient = openAiClient;
    }


    /**
     * OpenAI API를 호출해 사용자 질문과 가장 적합한 IndexEnum 키워드를 반환합니다.
     * @param inputText 사용자 질문 텍스트
     * @return 가장 적합한 IndexEnum 키워드
     */
    public IndexEnum getIndex(String inputText) {
        // 1. 프롬프트 생성
        String prompt = generatePrompt(inputText);
        // 2. OpenAI 호출
        String response = openAiClient.query(prompt);
        // 3. 응답 처리 및 IndexEnum 값 추출
        return extractBestIndex(response);
    }

    /**
     * OpenAI 프롬프트 생성
     * @param inputText 사용자 질문 텍스트
     * @return 프롬프트 문자열
     */
    public String generatePrompt(String inputText) {
        // Enum 값들을 키워드 문자열로 변환하여 프롬프트 생성
        String keywords = Arrays.stream(IndexEnum.values())
                .map(IndexEnum::getKeyword)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");

        return """
        다음은 사용자의 질문입니다:
        "%s"

        아래는 키워드 목록입니다:
        [%s]

        사용자 질문과 가장 적합한 키워드를 선택해 반환하세요.
        반환 형식은 반드시 키워드 하나여야 하며, 다른 설명은 포함하지 마세요.
        """.formatted(inputText, keywords);
    }


    /**
     * OpenAI 응답에서 IndexEnum 값 추출
     * @param response OpenAI 응답 텍스트
     * @return 가장 적합한 IndexEnum 값
     */
    private IndexEnum extractBestIndex(String response) {
        // OpenAI 응답과 Enum 값의 키워드 비교
        for (IndexEnum indexEnum : IndexEnum.values()) {
            if (response.contains(indexEnum.getKeyword())) {
                return indexEnum; // 일치하는 Enum 값 반환
            }
        }
        throw new IllegalArgumentException("응답에서 적합한 키워드를 찾을 수 없습니다.");
    }





    public static String getAnswer(String inputText,String similarChunks,String prompt){
        return null;
    }

}
