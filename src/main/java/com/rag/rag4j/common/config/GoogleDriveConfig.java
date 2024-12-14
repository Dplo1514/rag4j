package com.rag.rag4j.common.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.drive.Drive;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import lombok.Getter;
import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.core.io.ResourceLoader;

@Getter
@Configuration
public class GoogleDriveConfig {

    @Value("${cloud.drive.credentials.file-path}")
    private String filePath;

    @Value("${cloud.drive.application.name}")
    private String applicationName;

    @Bean
    public Drive googleDriveService() throws GeneralSecurityException, IOException {
        FileInputStream stream = new FileInputStream(filePath);
        GoogleCredential credential = GoogleCredential.fromStream(stream)
            .createScoped(Collections.singletonList("https://www.googleapis.com/auth/drive"));
        NetHttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        return new Drive.Builder(
            transport,
            jsonFactory,
            credential
        ).setApplicationName(applicationName).build();
    }
}
