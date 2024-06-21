package com.xiaomi.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {
    String store(MultipartFile multipartFile);

    List<String> listFiles();

    Resource loadAsResource(String fileName);

    void delete(String filePath);

    void rename(String oldFileName, String newFileName);
}
