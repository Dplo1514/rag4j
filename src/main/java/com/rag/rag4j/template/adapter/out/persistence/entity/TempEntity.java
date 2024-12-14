package com.rag.rag4j.template.adapter.out.persistence.entity;

import com.rag.rag4j.common.adapter.out.persistence.entity.BaseEntity;
import com.rag.rag4j.common.enums.CommonStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TempEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String data;

    public static TempEntity from(String data) {
        return TempEntity.builder()
            .status(CommonStatus.ACTIVE)
            .data(data)
            .build();
    }

    public static TempEntity of(long id,String data) {
        return TempEntity.builder()
            .id(id)
            .status(CommonStatus.ACTIVE)
            .data(data)
            .build();
    }

}
