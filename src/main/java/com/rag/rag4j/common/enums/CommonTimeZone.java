package com.rag.rag4j.common.enums;

import java.util.TimeZone;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonTimeZone {
    ASIA_SEOUL(TimeZone.getTimeZone("Asia/Seoul"));

    private TimeZone zone;

}
