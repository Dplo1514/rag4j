package com.rag.rag4j.prompt.application.port.in;

import com.rag.rag4j.prompt.application.domain.service.ChromaDbAdapter;
import com.rag.rag4j.prompt.application.domain.service.OpenAIService;
import com.rag.rag4j.prompt.application.domain.service.PromptGenerateService;
import com.rag.rag4j.prompt.application.port.out.ChromaDbPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 이승현 : 유즈 케이스 구현
@Service
@RequiredArgsConstructor
public class UseCaseImpl implements UseCase {

    private final ChromaDbAdapter ChromaDbAdapter; // Chroma DB와의 통신을 위한 Port
    private final PromptGenerateService promptGenerateService; // Chroma DB와의 통신을 위한 Port
    private final OpenAIService openAIService; // Chroma DB와의 통신을 위한 Port

    @Override
    public String process(String inputText) {
        // Chroma DB에 텍스트 전달하고 유사도 높은 청크 받아오기
        String keyWord = String.valueOf(openAIService.getIndex(inputText));
        System.out.println("keyWord : " + keyWord);
        String similarChunks = ChromaDbAdapter.getSimilarChunks(inputText,keyWord);
        System.out.println("similarChunks : " + similarChunks);
        String prompt  = promptGenerateService.generate();
        System.out.println("prompt : " + prompt);
        return OpenAIService.getAnswer(inputText,similarChunks,prompt);
    }
}