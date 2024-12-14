package com.rag.rag4j.chunk.adapter.out;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.rag.rag4j.chunk.application.port.out.TokenCounter;

public class JTokkitTokenCounter implements TokenCounter {

    private final EncodingRegistry registry;
    private final Encoding encoding;

    public JTokkitTokenCounter() {
        this.registry = Encodings.newDefaultEncodingRegistry();
        this.encoding = registry.getEncoding("cl100k_base").get();
    }

    @Override
    public int countTokens(String text) {
        return encoding.countTokens(text);
    }
}
