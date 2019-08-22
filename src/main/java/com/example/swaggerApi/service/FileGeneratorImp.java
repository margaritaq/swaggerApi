package com.example.swaggerApi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import java.io.*;

public class FileGeneratorImp implements FileGeneratorInterface {
    private final static Logger log = LoggerFactory.getLogger(FileGeneratorImp.class);

        public ResponseEntity<Object> downloadFile(int fileId, String destination) {
        try {
            String fileName = destination + fileId + ".txt";

            FileWriter writer = new FileWriter(fileName);
            writer.write(String.valueOf(fileId));
            writer.flush();
            writer.close();

            File file = new File(fileName);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");

            ResponseEntity<Object> responseEntity = ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/txt"))
                    .body(resource);
            return responseEntity;
        } catch (Exception e) {
            log.debug("Error encountered when downloading file!");
            return new ResponseEntity<>("error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}