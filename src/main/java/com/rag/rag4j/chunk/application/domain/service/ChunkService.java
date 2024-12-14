package com.rag.rag4j.chunk.application.domain.service;

import com.rag.rag4j.chunk.application.dto.command.ChunkCommand;
import com.rag.rag4j.chunk.application.port.in.ChunkUseCase;
import com.rag.rag4j.common.documents.UseCase;

@UseCase
public class ChunkService implements ChunkUseCase {

    @Override
    public boolean chunk(ChunkCommand command) {
        return false;
    }
    
}
