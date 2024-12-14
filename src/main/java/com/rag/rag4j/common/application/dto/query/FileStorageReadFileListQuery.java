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
public class FileStorageReadFileListQuery {

    @Builder.Default
    int pageSize = 20;

    @Builder.Default
    String pageToken = "";

    public static FileStorageReadFileListQuery of(
        int pageSize,
        String pageToken
    ) {
        return FileStorageReadFileListQuery.builder()
            .pageSize(pageSize)
            .pageToken(pageToken)
            .build();
    }

}