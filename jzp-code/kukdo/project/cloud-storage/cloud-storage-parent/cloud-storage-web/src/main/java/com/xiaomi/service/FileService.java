package com.xiaomi.service;

import com.xiaomi.model.FileMetadata;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    FileMetadata storeFile(MultipartFile file);

    List<String> getAllFiles();

    FileMetadata getFileMetadata(Long fileId);

    Resource fetchFile(String filePath);

    void deleteFile(Long fileId);

    void renameFile(Long id, String newName);

}
