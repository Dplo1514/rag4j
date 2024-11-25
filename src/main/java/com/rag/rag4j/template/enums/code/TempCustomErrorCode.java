package com.rag.rag4j.template.enums.code;

import com.rag.rag4j.common.enums.code.ICommonCustomCode;
import com.rag.rag4j.common.exception.response.ICommonResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TempCustomErrorCode implements ICommonResponseCode {

    DEFAULT(HttpStatus.INTERNAL_SERVER_ERROR, TempErrorCode.DEFAULT, "internal server error"),
    ;

    private final HttpStatus httpStatus;

    private final ICommonCustomCode customCode;

    private final String message;

}
