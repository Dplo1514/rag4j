package com.rag.rag4j.parsing.application.dto;

import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ParsingMongodbUploadDto {
    String type;
    String title;
    String contents;
    String status;

    public static ParsingMongodbUploadDto of(String type, String title, String contents, String status) {
        return ParsingMongodbUploadDto.builder()
                .type(type)
                .title(title)
                .contents(contents)
                .status(status)
                .build();
    }
}
