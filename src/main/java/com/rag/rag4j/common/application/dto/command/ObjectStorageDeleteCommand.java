package com.rag.rag4j.common.application.dto.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectStorageDeleteCommand {

    private String signature;

    public static ObjectStorageDeleteCommand of(String signature) {
        return ObjectStorageDeleteCommand.builder()
            .signature(signature)
            .build();
    }

}