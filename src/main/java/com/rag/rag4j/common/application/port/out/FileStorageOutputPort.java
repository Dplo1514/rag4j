package com.rag.rag4j.common.application.port.out;

import com.rag.rag4j.common.application.dto.FileStorageGetFileDto;
import com.rag.rag4j.common.application.dto.FileStorageReadFileListDto;
import com.rag.rag4j.common.application.dto.query.FileStorageGetFileQuery;
import com.rag.rag4j.common.application.dto.query.FileStorageReadFileListQuery;
import com.rag.rag4j.common.documents.port.InfraOutputPort;

@InfraOutputPort
public interface FileStorageOutputPort {

    FileStorageReadFileListDto readFileList(FileStorageReadFileListQuery query);

    FileStorageGetFileDto getFile(FileStorageGetFileQuery query);

}
