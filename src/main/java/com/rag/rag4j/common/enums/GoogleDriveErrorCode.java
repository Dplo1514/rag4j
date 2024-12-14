package com.rag.rag4j.common.enums;

import com.rag.rag4j.common.enums.code.AwsCustomErrorCode;
import com.rag.rag4j.common.enums.code.GoogleDriveCustomErrorCode;
import com.rag.rag4j.common.exception.response.ICommonResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GoogleDriveErrorCode implements ICommonResponseCode {

    DEFAULT(HttpStatus.INTERNAL_SERVER_ERROR, GoogleDriveCustomErrorCode.DEFAULT.getCode(), GoogleDriveCustomErrorCode.DEFAULT.getMessage()),
    READ_FILE_LIST_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, GoogleDriveCustomErrorCode.READ_FILE_LIST_FAILURE.getCode(), GoogleDriveCustomErrorCode.READ_FILE_LIST_FAILURE.getMessage()),
    GET_FILE_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, GoogleDriveCustomErrorCode.GET_FILE_FAILURE.getCode(), GoogleDriveCustomErrorCode.GET_FILE_FAILURE.getMessage()),
    ;

    private final HttpStatus httpStatus;

    private final long customCode;

    private final String message;


}
