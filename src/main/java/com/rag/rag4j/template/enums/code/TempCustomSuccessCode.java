package com.rag.rag4j.template.enums.code;

import com.rag.rag4j.common.enums.code.ICommonCustomCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Temp Api Success Code
 * 단위 : 500 ~ 1000
 * @author : Plo
 */
@Getter
@AllArgsConstructor
public enum TempCustomSuccessCode implements ICommonCustomCode {
    DEFAULT(500)
    ;

    private final long code;

}
