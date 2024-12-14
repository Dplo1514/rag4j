package com.rag.rag4j.common.application.port.out;

import com.rag.rag4j.common.application.dto.ObjectStorageGetObjectStreamDto;
import com.rag.rag4j.common.application.dto.ObjectStorageGetObjectUrlDto;
import com.rag.rag4j.common.application.dto.ObjectStorageUploadDto;
import com.rag.rag4j.common.application.dto.command.ObjectStorageDeleteCommand;
import com.rag.rag4j.common.application.dto.command.ObjectStorageUploadCommand;
import com.rag.rag4j.common.application.dto.query.ObjectStorageGetQuery;
import com.rag.rag4j.common.documents.port.InfraOutputPort;

@InfraOutputPort
public interface ObjectStorageOutPutPort {

    ObjectStorageUploadDto uploadObject(ObjectStorageUploadCommand command);

    ObjectStorageGetObjectUrlDto getObjectUrl(ObjectStorageGetQuery query);

    ObjectStorageGetObjectStreamDto getObjectStream(ObjectStorageGetQuery query);

    void deleteObject(ObjectStorageDeleteCommand command);


}
