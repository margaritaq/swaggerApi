package com.example.swaggerApi.rest;

import com.example.swaggerApi.service.FileGeneratorImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class RestWebController {


    @Value("${files.path}")
public String destination;
@RequestMapping("/files/{fileId}")
public ResponseEntity<Object> getFile(@PathVariable("fileId")int fileId){
    FileGeneratorImp file = new FileGeneratorImp();
    ResponseEntity<Object> response = file.downloadFile(fileId, destination);


    return response;
}

}
