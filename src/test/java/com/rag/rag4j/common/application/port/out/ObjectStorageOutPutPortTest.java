package com.rag.rag4j.common.application.port.out;

import static org.mockito.BDDMockito.given;

import com.rag.rag4j.UnitTestSupport;
import com.rag.rag4j.common.application.dto.ObjectStorageGetObjectUrlDto;
import com.rag.rag4j.common.application.dto.ObjectStorageUploadDto;
import com.rag.rag4j.common.application.dto.command.ObjectStorageDeleteCommand;
import com.rag.rag4j.common.application.dto.command.ObjectStorageUploadCommand;
import com.rag.rag4j.common.application.dto.query.ObjectStorageGetQuery;
import java.io.File;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class ObjectStorageOutPutPortTest extends UnitTestSupport {

    @Mock
    private ObjectStorageOutPutPort sut;

    @Test
    @DisplayName("object upload is success")
    void upload_success() {
        // given
        String url = "url";
        String signature = "sig";
        ObjectStorageUploadCommand command = ObjectStorageUploadCommand.of(
            signature,
            new File("test", "test")
        );

        ObjectStorageUploadDto dto = ObjectStorageUploadDto.of(
            url,
            signature,
            true
        );

        // given
        given(sut.uploadObject(command)).willReturn(dto);

        // when
        ObjectStorageUploadDto result = sut.uploadObject(command);

        // then
        Assertions.assertThat(result.getSignature()).isEqualTo(signature);
        Assertions.assertThat(result.getUrl()).isEqualTo(url);
        Assertions.assertThat(result.isSuccess()).isTrue();
    }

    @Test
    @DisplayName("object get is success")
    void get_object_success() {
        // given
        String signature = "sig";
        String url = "url";
        ObjectStorageGetQuery query = ObjectStorageGetQuery.of(
            signature
        );

        ObjectStorageGetObjectUrlDto dto = ObjectStorageGetObjectUrlDto.of(
            url,
            signature
        );

        // given
        given(sut.getObjectUrl(query)).willReturn(dto);

        // when
        ObjectStorageGetObjectUrlDto result = sut.getObjectUrl(query);

        // then
        Assertions.assertThat(result.getSignature()).isEqualTo(signature);
        Assertions.assertThat(result.getUrl()).isEqualTo(url);
    }

    @Test
    @DisplayName("object delete is success")
    void delete_success() {
        // given
        String signature = "sig";
        ObjectStorageDeleteCommand command = ObjectStorageDeleteCommand.of(
            signature
        );

        // when -> then
        sut.deleteObject(command);
    }

}