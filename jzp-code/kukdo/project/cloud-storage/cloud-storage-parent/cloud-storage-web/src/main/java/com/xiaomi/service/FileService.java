package com.xiaomi.service;

import com.xiaomi.model.FileMetadata;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileMetadata storeFile(MultipartFile file);

    FileMetadata getFileMetadata(Long fileId);

    void deleteFile(Long fileId);

    void renameFile(Long id, String newName);

    Resource loadFileAsResource(String filePath);
}
