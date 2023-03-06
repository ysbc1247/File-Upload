package com.filetest.fileupload.controller;

import com.filetest.fileupload.domain.FileEntity;
import com.filetest.fileupload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    private FileService fileService;
    @PostMapping("/upload")
    public ResponseEntity<FileEntity> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        FileEntity fileEntity = fileService.uploadFile(file);
        return new ResponseEntity<>(fileEntity, HttpStatus.OK);
    }
}
