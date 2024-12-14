package com.rag.rag4j.common.enums.code;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AWS 호출시 Error Code
 * 단위 : 10100 ~ 10199
 * @author : PLO
 */
@Getter
@AllArgsConstructor
public enum GoogleDriveCustomErrorCode implements ICommonCustomCode {
    DEFAULT(10100, "internal server error"),
    READ_FILE_LIST_FAILURE(10101, "internal server error"),
    GET_FILE_FAILURE(10102, "internal server error"),
    ;

    private final long code;

    private final String message;

}
