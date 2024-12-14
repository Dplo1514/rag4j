package com.rag.rag4j.template.enums.code;

import com.rag.rag4j.common.enums.code.ICommonCustomCode;
import com.rag.rag4j.common.exception.response.ICommonResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TempErrorCode implements ICommonResponseCode {

    DEFAULT(HttpStatus.INTERNAL_SERVER_ERROR, TempCustomErrorCode.DEFAULT.getCode(), TempCustomErrorCode.DEFAULT.getMessage())
    ;

    private final HttpStatus httpStatus;

    private final long customCode;

    private final String message;

}
