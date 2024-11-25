package com.rag.rag4j.common.application.domain.model;

import com.rag.rag4j.common.enums.CommonStatus;
import java.time.LocalDateTime;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class BaseDomain {

    protected CommonStatus status;

    private LocalDateTime createdAt;

    private long createdAtEpochTime;

    private LocalDateTime updatedAt;

    private long updatedAtEpochTime;

    protected long convertToEpochSecond(LocalDateTime dateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zoneDateTime = dateTime.atZone(zoneId);
        return zoneDateTime.toEpochSecond();
    }

}
