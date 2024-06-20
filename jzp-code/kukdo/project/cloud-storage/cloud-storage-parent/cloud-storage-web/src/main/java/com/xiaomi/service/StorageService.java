package com.xiaomi.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String store(MultipartFile multipartFile);

    Resource loadAsResource(String fileName);

    void delete(String filePath);

    void rename(String oldFileName, String newFileName);
}
