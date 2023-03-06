package com.filetest.fileupload.service;

import com.filetest.fileupload.domain.FileEntity;
import com.filetest.fileupload.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;
    public FileEntity uploadFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(fileName);
        fileEntity.setSize(file.getSize());
        fileEntity.setPath("/uploads/" + fileName);
        File uploadDir = new File("uploads");
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String filePath = uploadDir.getAbsolutePath() + "/" + fileName;
        File dest = new File(filePath);
        file.transferTo(dest);
        return fileRepository.save(fileEntity);
    }
}
