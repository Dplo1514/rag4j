package com.rag.rag4j.common.exception;

import com.rag.rag4j.common.exception.response.ICommonResponseCode;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {

    private ICommonResponseCode code;

    public GlobalException(ICommonResponseCode code) {
        super(code.getMessage());
        this.code = code;
    }

}
