package com.rag.rag4j.common.application.dto;

import java.io.File;
import java.util.List;
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
public class FileStorageReadFileListDto {

    private List<String> paths;

    private boolean hasNextPage;

    private String nextPageToken;

    public static FileStorageReadFileListDto of(List<String> paths, boolean hasNextPage, String nextPageToken){
        return FileStorageReadFileListDto.builder()
            .paths(paths)
            .hasNextPage(hasNextPage)
            .nextPageToken(nextPageToken)
            .build();
    }

}