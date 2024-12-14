package com.rag.rag4j.common.adapter.out.infra;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.rag.rag4j.common.application.dto.ObjectStorageGetObjectDto;
import com.rag.rag4j.common.application.dto.ObjectStorageUploadDto;
import com.rag.rag4j.common.application.dto.command.ObjectStorageDeleteCommand;
import com.rag.rag4j.common.application.dto.command.ObjectStorageUploadCommand;
import com.rag.rag4j.common.application.dto.query.ObjectStorageGetQuery;
import com.rag.rag4j.common.application.port.out.ObjectStorageOutPutPort;
import com.rag.rag4j.common.config.AwsConfig;
import com.rag.rag4j.common.documents.adaptor.InfraOutputAdaptor;
import com.rag.rag4j.common.enums.AwsErrorCode;
import com.rag.rag4j.common.exception.GlobalException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@InfraOutputAdaptor
@RequiredArgsConstructor
public class AwsObjectStorageAdaptor implements ObjectStorageOutPutPort {

    private final AwsConfig s3Config;

    private final AmazonS3 awsS3;

    @Override
    public ObjectStorageUploadDto uploadObject(ObjectStorageUploadCommand command) {
        String bucket = s3Config.getBucket();
        File file = command.getFile(); // 실제 File 객체
        String signature = command.getSignature();

        // InputStream 선언
        try (InputStream inputStream = new FileInputStream(file)) {
            ObjectMetadata metadata = new ObjectMetadata();

            // 파일 크기 및 Content-Type 설정
            metadata.setContentLength(file.length());
            String contentType = Files.probeContentType(file.toPath());
            // S3 업로드 요청 생성
            PutObjectRequest request = new PutObjectRequest(bucket, signature, inputStream, metadata);
            awsS3.putObject(request);

            // 업로드된 파일의 URL 생성
            URL url = awsS3.getUrl(bucket, signature);

            return ObjectStorageUploadDto.of(url.toString(), signature, true);

        } catch (IOException e) {
            log.error("파일 처리 중 오류 발생: {}", e.getMessage());
            throw new GlobalException(AwsErrorCode.FILE_UPLOAD_FAILURE);
        } catch (AmazonS3Exception e) {
            log.error("S3 업로드 중 오류 발생: {}", e.getMessage());
            throw new GlobalException(AwsErrorCode.FILE_UPLOAD_FAILURE);
        }
    }


    @Override
    public ObjectStorageGetObjectDto getObject(ObjectStorageGetQuery query) {
        String bucket = s3Config.getBucket();
        try {
            String signature = query.getSignature();
            URL url = awsS3.getUrl(bucket, signature);
            return ObjectStorageGetObjectDto.of(url.toString(), signature);
        } catch (AmazonS3Exception e) {
            log.error(e.getMessage());
            throw new GlobalException(AwsErrorCode.FILE_READ_FAILURE);
        }
    }

    @Override
    public void deleteObject(ObjectStorageDeleteCommand command) {
        String bucket = s3Config.getBucket();
        try {
            String signature = command.getSignature();
            DeleteObjectRequest request = new DeleteObjectRequest(bucket, signature);
            awsS3.deleteObject(request);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new GlobalException(AwsErrorCode.FILE_DELETE_FAILURE);
        }
    }

}
