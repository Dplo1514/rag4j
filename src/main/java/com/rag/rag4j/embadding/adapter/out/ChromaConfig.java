package com.rag.rag4j.embadding.adapter.out;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ChromaConfig {

    @Value("${chroma.host}")
    private String host;

    @Value("${chroma.username}")
    private String username;

    @Value("${chroma.password}")
    private String password;
}
