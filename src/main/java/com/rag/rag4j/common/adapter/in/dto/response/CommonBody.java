package com.rag.rag4j.common.adapter.in.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonBody<T> implements ICommonBody<T>{

    private long code;

    private String message;

    private T data;

    public static <T> CommonBody <T> of(long code, String message, T data) {
        return new CommonBodyBuilder<T>()
            .code(code)
            .message(message)
            .data(data)
            .build();
    }
}