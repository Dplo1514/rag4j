package com.rag.rag4j.common.enums;

import com.rag.rag4j.common.enums.code.AwsCustomErrorCode;
import com.rag.rag4j.common.enums.code.ICommonCustomCode;
import com.rag.rag4j.common.exception.response.ICommonResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AwsErrorCode implements ICommonResponseCode {

    DEFAULT(HttpStatus.INTERNAL_SERVER_ERROR, AwsCustomErrorCode.DEFAULT.getCode(), AwsCustomErrorCode.DEFAULT.getMessage()),
    FILE_UPLOAD_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, AwsCustomErrorCode.UPLOAD_FAILURE.getCode(), AwsCustomErrorCode.UPLOAD_FAILURE.getMessage()),
    FILE_READ_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, AwsCustomErrorCode.READ_FAILURE.getCode(), AwsCustomErrorCode.READ_FAILURE.getMessage()),
    FILE_DELETE_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, AwsCustomErrorCode.DELETE_FAILURE.getCode(), AwsCustomErrorCode.DELETE_FAILURE.getMessage()),
    READ_SIGNATURE_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, AwsCustomErrorCode.READ_SIGNATURE_FAILURE.getCode(), AwsCustomErrorCode.READ_SIGNATURE_FAILURE.getMessage()),
    ;

    private final HttpStatus httpStatus;

    private final long customCode;

    private final String message;


}
