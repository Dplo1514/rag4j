//package com.rag.rag4j.prompt.adapter.out.persistence.entity;
//
//import com.rag.rag4j.common.adapter.out.persistence.entity.BaseEntity;
//import com.rag.rag4j.common.enums.CommonStatus;
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.experimental.SuperBuilder;
//
//@Getter
//@Entity
//@SuperBuilder
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
//public class ChunkEntity extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;
//
//    @Column
//    private String data;
//
//    public static ChunkEntity from(String data) {
//        return ChunkEntity.builder()
//            .status(CommonStatus.ACTIVE)
//            .data(data)
//            .build();
//    }
//
//    public static ChunkEntity of(long id, String data) {
//        return ChunkEntity.builder()
//            .id(id)
//            .status(CommonStatus.ACTIVE)
//            .data(data)
//            .build();
//    }
//
//}
// 벡터 DB에서 청크 엔티티 넘어올 예정?