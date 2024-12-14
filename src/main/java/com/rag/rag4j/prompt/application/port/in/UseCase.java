package com.rag.rag4j.prompt.application.port.in;

// 유스케이스 인터페이스: 사용자 입력 처리 정의
public interface UseCase {
    String process(String inputText);
}