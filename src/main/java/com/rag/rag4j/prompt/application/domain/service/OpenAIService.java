package com.rag.rag4j.prompt.application.domain.service;

import com.rag.rag4j.common.documents.UseCase;
import com.rag.rag4j.template.application.port.in.TempUseCase;

@UseCase
public class OpenAIService implements TempUseCase {
    public static String getIndex(String inputText){
        return null;
    }

    public static String getAnswer(String inputText,String similarChunks,String prompt){
        return null;
    }


}
