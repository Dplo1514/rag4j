package com.rag.rag4j.common.application.port.out;

import com.rag.rag4j.common.application.dto.ObjectStorageGetObjectDto;
import com.rag.rag4j.common.application.dto.ObjectStorageUploadDto;
import com.rag.rag4j.common.application.dto.command.ObjectStorageDeleteCommand;
import com.rag.rag4j.common.application.dto.command.ObjectStorageUploadCommand;
import com.rag.rag4j.common.application.dto.query.ObjectStorageGetQuery;
import com.rag.rag4j.common.documents.port.InfraOutputPort;

@InfraOutputPort
public interface ObjectStorageOutPutPort {

    ObjectStorageUploadDto uploadObject(ObjectStorageUploadCommand command);

    ObjectStorageGetObjectDto getObject(ObjectStorageGetQuery query);

    void deleteObject(ObjectStorageDeleteCommand command);


}
