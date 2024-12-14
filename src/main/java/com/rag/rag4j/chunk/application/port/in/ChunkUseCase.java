package com.rag.rag4j.chunk.application.port.in;

import com.rag.rag4j.chunk.application.dto.command.ChunkCommand;

public interface ChunkUseCase {
    boolean chunk(ChunkCommand command);
}
