package com.rag.rag4j.common.exception.response;

import com.rag.rag4j.common.enums.code.ICommonCustomCode;
import org.springframework.http.HttpStatus;

public interface ICommonResponseCode {

    HttpStatus getHttpStatus();

    String getMessage();

    ICommonCustomCode getCustomCode();

}
