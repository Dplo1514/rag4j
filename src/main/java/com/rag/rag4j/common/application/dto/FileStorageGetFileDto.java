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
public class FileStorageGetFileDto {

    private File file;

    public static FileStorageGetFileDto from(File file){
        return FileStorageGetFileDto.builder()
            .file(file)
            .build();
    }

}