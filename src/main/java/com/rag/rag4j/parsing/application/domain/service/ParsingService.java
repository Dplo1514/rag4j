package com.rag.rag4j.parsing.application.domain.service;

import com.rag.rag4j.common.application.dto.ObjectStorageGetObjectStreamDto;
import com.rag.rag4j.common.application.dto.query.ObjectStorageGetQuery;
import com.rag.rag4j.common.application.port.out.ObjectStorageOutPutPort;
import com.rag.rag4j.common.documents.UseCase;
import com.rag.rag4j.parsing.application.dto.ParsingMongodbUploadDto;
import com.rag.rag4j.parsing.application.port.in.ParsingUseCase;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;

@UseCase
public class ParsingService implements ParsingUseCase {

    ObjectStorageOutPutPort sop;

    @Override
    public void parsing() {
        // consume event
        String signature = "";
        String type = "";

        // get File
        File file = getFile(signature);

        // convert to text
        String contents = convertToText(file);
        String title = getTitle(file);

        // convert to json
        ParsingMongodbUploadDto uploadDto = ParsingMongodbUploadDto.of(type, title, contents, "SUCCESS");

        // save mongoDB


        // publish event
    }

    private String getTitle(File file) {
        String title = file.getName();
        return title;
    }

    private String convertToText(File file) {
        String contents;
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            StringBuilder extractedText = new StringBuilder();
            int totalPages = document.getNumberOfPages();

            for (int i = 1; i <= totalPages; i++) {
                pdfTextStripper.setStartPage(i);
                pdfTextStripper.setEndPage(i);
                extractedText.append(pdfTextStripper.getText(document));

                if (i < totalPages) {
                    extractedText.append("\n#\n");
                }
            }

            contents = extractedText.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return contents;
    }

    private File getFile(String signature) {
        ObjectStorageGetQuery query = ObjectStorageGetQuery.of(signature);
        ObjectStorageGetObjectStreamDto streamDto = sop.getObjectStream(query);
        InputStream stream = streamDto.getStream();

        File file = convertInputStreamToFile(stream, signature);

        return file;
    }

    private File convertInputStreamToFile(InputStream inputStream, String filePath) {
        File file = new File(filePath);
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}
