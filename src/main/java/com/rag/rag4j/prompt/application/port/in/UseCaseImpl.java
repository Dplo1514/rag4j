package com.rag.rag4j.prompt.application.port.in;

import com.rag.rag4j.prompt.application.domain.service.OpenAIService;
import com.rag.rag4j.prompt.application.domain.service.PromptGenerateService;
import com.rag.rag4j.prompt.application.port.out.ChromaDbPort;
import org.springframework.stereotype.Service;

@Service
public class UseCaseImpl implements UseCase {

    private final ChromaDbPort chromaDbPort; // Chroma DB와의 통신을 위한 Port
    private final PromptGenerateService promptGenerateService; // Chroma DB와의 통신을 위한 Port
    private final OpenAIService openAIService; // Chroma DB와의 통신을 위한 Port

    public UseCaseImpl(ChromaDbPort chromaDbPort, PromptGenerateService promptGenerateService, OpenAIService openAIService) {
        this.chromaDbPort = chromaDbPort;
        this.promptGenerateService = promptGenerateService;
        this.openAIService = openAIService;
    }

    @Override
    public String process(String inputText) {
        // Chroma DB에 텍스트 전달하고 유사도 높은 청크 받아오기

        String keyWord = openAIService.getIndex(inputText);
        String similarChunks = chromaDbPort.getSimilarChunks(inputText);
        String prompt  = promptGenerateService.generate();
        return openAIService.getAnswer(inputText,similarChunks,prompt);
    }
}