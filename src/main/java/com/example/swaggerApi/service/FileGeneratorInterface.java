package com.example.swaggerApi.service;

import org.springframework.http.ResponseEntity;

public interface FileGeneratorInterface {
    ResponseEntity<Object> downloadFile(int fileId, String destination);
}
