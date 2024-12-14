package com.rag.rag4j.common.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic rag4jTopic() {
        return new NewTopic("rag4j-topic", 1, (short) 1); // 토픽 이름, 파티션 수, 복제 수
    }

}
