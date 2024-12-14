package com.rag.rag4j.common.enums.code;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AWS 호출시 Error Code
 * 단위 : 10000 ~ 10099
 * @author : PLO
 */
@Getter
@AllArgsConstructor
public enum AwsCustomErrorCode implements ICommonCustomCode {
    DEFAULT(10000, "internal server error"),
    UPLOAD_FAILURE(10001, "internal server error"),
    READ_FAILURE(10002, "internal server error"),
    DELETE_FAILURE(10003, "internal server error"),
    READ_SIGNATURE_FAILURE(10004, "internal server error"),
    ;

    private final long code;

    private final String message;

}
