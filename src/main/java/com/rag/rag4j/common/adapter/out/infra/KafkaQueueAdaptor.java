package com.rag.rag4j.common.adapter.out.infra;

import com.rag.rag4j.common.application.port.out.EventQueueOutPutPort;
import com.rag.rag4j.common.documents.adaptor.InfraOutputAdaptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@InfraOutputAdaptor
@RequiredArgsConstructor
public class KafkaQueueAdaptor implements EventQueueOutPutPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send("rag4j-topic", message);
    }

    @KafkaListener(topics = "rag4j-topic", groupId = "rag4j-group")
    public void consumeMessage(String message) {
        System.out.println("Consumed message: " + message);
    }

}