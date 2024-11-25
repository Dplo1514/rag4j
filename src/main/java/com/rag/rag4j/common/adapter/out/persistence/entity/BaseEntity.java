package com.rag.rag4j.common.adapter.out.persistence.entity;

import com.rag.rag4j.common.enums.CommonStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    protected CommonStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

    private long createdAtEpochTime;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private long updatedAtEpochTime;

    @PrePersist
    protected void onCreate() {
        this.createdAtEpochTime = convertToEpochSecond(this.createdAt);
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAtEpochTime = convertToEpochSecond(this.updatedAt);
    }

    protected long convertToEpochSecond(LocalDateTime dateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zoneDateTime = dateTime.atZone(zoneId);
        return zoneDateTime.toEpochSecond();
    }

}
