package com.rag.rag4j.common.adapter.in.dto.response;

public interface ICommonBody<T> {

    long getCode();

    String getMessage();

    T getData();

}