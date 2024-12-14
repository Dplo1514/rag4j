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
public class ObjectStorageUploadDto {

    private String url;

    private String signature;

    private boolean isSuccess;

    public static ObjectStorageUploadDto of(String url, String signature, boolean isSuccess){
        return ObjectStorageUploadDto.builder()
            .url(url)
            .signature(signature)
            .isSuccess(isSuccess)
            .build();
    }

}