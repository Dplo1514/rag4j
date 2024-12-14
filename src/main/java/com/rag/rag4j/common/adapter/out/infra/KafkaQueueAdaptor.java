package com.rag.rag4j.common.adapter.out.infra;

import com.rag.rag4j.common.application.port.out.EventQueueOutPutPort;
import com.rag.rag4j.common.application.port.out.ObjectStorageOutPutPort;
import com.rag.rag4j.common.documents.adaptor.InfraOutputAdaptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@InfraOutputAdaptor
@RequiredArgsConstructor
public class KafkaQueueAdaptor implements EventQueueOutPutPort {

}