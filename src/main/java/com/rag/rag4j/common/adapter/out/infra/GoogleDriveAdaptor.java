package com.rag.rag4j.common.adapter.out.infra;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.rag.rag4j.common.application.dto.FileStorageGetFileDto;
import com.rag.rag4j.common.application.dto.FileStorageReadFileListDto;
import com.rag.rag4j.common.application.dto.query.FileStorageGetFileQuery;
import com.rag.rag4j.common.application.dto.query.FileStorageReadFileListQuery;
import com.rag.rag4j.common.application.port.out.FileStorageOutputPort;
import com.rag.rag4j.common.documents.adaptor.InfraOutputAdaptor;
import com.rag.rag4j.common.enums.GoogleDriveErrorCode;
import com.rag.rag4j.common.exception.GlobalException;
import io.micrometer.common.util.StringUtils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@InfraOutputAdaptor
@RequiredArgsConstructor
public class GoogleDriveAdaptor implements FileStorageOutputPort {

    private final Drive googleDrive;

    @Override
    public FileStorageReadFileListDto readFileList(FileStorageReadFileListQuery query) {
        try {
            FileList result = googleDrive.files().list()
                .setQ("trashed=false") // 휴지통 파일 제외
                .setFields("nextPageToken, files(id, name, mimeType, parents, size, createdTime, modifiedTime, webViewLink, owners(displayName, emailAddress), shared)") // 추가 필드 요청
                .setPageSize(query.getPageSize())
                .setPageToken(query.getPageToken())
                .execute();

            List<File> files = result.getFiles();
            List<String> paths = files.stream().map(
                File::getName
            ).toList();

            String nextPageToken = result.getNextPageToken();
            return FileStorageReadFileListDto.of(
                paths,
                StringUtils.isBlank(nextPageToken),
                nextPageToken
            );
        } catch (IOException e) {
            log.error("Google Drive File 조회 중 오류 발생: {}", e.getMessage());
            throw new GlobalException(GoogleDriveErrorCode.READ_FILE_LIST_FAILURE);
        }
    }

    @Override
    public FileStorageGetFileDto getFile(FileStorageGetFileQuery query) {
        try {
            String filePath = query.getFilePath();
            FileList result = googleDrive.files().list()
                .setQ(String.format("name='%s' and trashed=false", filePath)) // 파일 이름 검색
                .setFields("files(id, name)")
                .execute();

            List<com.google.api.services.drive.model.File> files = result.getFiles();
            if (files.isEmpty()) {
                throw new RuntimeException("파일을 찾을 수 없습니다: " + filePath);
            }

            com.google.api.services.drive.model.File driveFile = files.get(0);

            java.io.File tempFile = java.io.File.createTempFile(driveFile.getName(), null);

            try (OutputStream outputStream = new FileOutputStream(tempFile)) {
                googleDrive.files().get(driveFile.getId())
                    .executeMediaAndDownloadTo(outputStream);
            }

            return FileStorageGetFileDto.from(tempFile);


        } catch (IOException e) {
            log.error("Google Drive에서 파일을 가져오는 중 오류 발생: {}", e.getMessage(), e);
            throw new RuntimeException("파일 다운로드 실패: " + query.getFilePath(), e);
        }
    }

}