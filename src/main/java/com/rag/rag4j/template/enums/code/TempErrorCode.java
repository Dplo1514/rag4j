package com.rag.rag4j.template.enums.code;

import com.rag.rag4j.common.enums.code.ICommonCustomCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Template API 관련 Error Code
 * 단위 : 0 ~ 500
 * @author : Plo
 */
@Getter
@AllArgsConstructor
public enum TempErrorCode implements ICommonCustomCode {
    DEFAULT(0)
    ;

    private final long code;

}
