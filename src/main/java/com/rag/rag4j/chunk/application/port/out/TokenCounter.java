package com.rag.rag4j.chunk.application.port.out;

public interface TokenCounter {
    int countTokens(String text);
}
