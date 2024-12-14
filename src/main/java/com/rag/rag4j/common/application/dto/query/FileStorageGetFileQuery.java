package com.rag.rag4j.common.application.dto.query;

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
public class FileStorageGetFileQuery {

    String filePath;

    public static FileStorageGetFileQuery of(
        String filePath
    ) {
        return FileStorageGetFileQuery.builder()
            .filePath(filePath)
            .build();
    }

}