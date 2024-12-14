package com.rag.rag4j.common.application.dto;

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
public class ObjectStorageGetObjectUrlDto {

    private String url;

    private String signature;

    public static ObjectStorageGetObjectUrlDto of(String url, String signature){
        return ObjectStorageGetObjectUrlDto.builder()
            .url(url)
            .signature(signature)
            .build();
    }

}