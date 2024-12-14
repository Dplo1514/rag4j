package com.rag.rag4j.common;

import com.rag.rag4j.common.adapter.out.infra.GoogleDriveAdaptor;
import com.rag.rag4j.common.application.dto.FileStorageReadFileListDto;
import com.rag.rag4j.common.application.dto.query.FileStorageGetFileQuery;
import com.rag.rag4j.common.application.dto.query.FileStorageReadFileListQuery;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestCont {

    private final GoogleDriveAdaptor adaptor;

    @GetMapping("/test")
    void test(){
        FileStorageReadFileListQuery query = FileStorageReadFileListQuery.of(
            20,
            ""
        );

        FileStorageReadFileListDto dto = adaptor.readFileList(query);
        List<String> paths = dto.getPaths();
        for (String path : paths) {
            FileStorageGetFileQuery getFileQuery = FileStorageGetFileQuery.of(path);
            adaptor.getFile(getFileQuery);
        }
    }
}
