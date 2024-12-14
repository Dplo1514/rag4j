//package com.rag.rag4j.common.application.port.out;
//
//import com.rag.rag4j.UnitTestSupport;
//import com.rag.rag4j.common.application.dto.FileStorageReadFileListDto;
//import com.rag.rag4j.common.application.dto.query.FileStorageReadFileListQuery;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.BDDMockito.when;
//import static org.mockito.Mockito.mock;
//
//class FileStorageOutputPortTest extends UnitTestSupport {
//
//    @Mock
//    private FileStorageOutputPort sut;
//
//    @Test
//    @DisplayName("")
//    void getFileList(){
//        FileStorageReadFileListQuery query = mock(FileStorageReadFileListQuery.class);
//        FileStorageReadFileListDto dto = mock(FileStorageReadFileListDto.class);
//        when(sut.readFileList(query)).thenReturn(dto);
//    }
//
//}