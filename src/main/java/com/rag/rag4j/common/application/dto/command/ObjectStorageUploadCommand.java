package com.rag.rag4j.common.application.dto.command;

import java.io.File;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@ToString
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectStorageUploadCommand {

    private String signature;

    private File file;

    public static ObjectStorageUploadCommand of(String signature, File file) {
        return ObjectStorageUploadCommand.builder()
            .signature(signature)
            .file(file)
            .build();
    }

}