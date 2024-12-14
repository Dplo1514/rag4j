package com.rag.rag4j.common.application.dto;

import java.io.InputStream;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectStorageGetObjectStreamDto {

    private String signature;

    private InputStream stream;

    public static ObjectStorageGetObjectStreamDto of(String signature,InputStream stream){
        return ObjectStorageGetObjectStreamDto.builder()
            .signature(signature)
            .stream(stream)
            .build();
    }

}