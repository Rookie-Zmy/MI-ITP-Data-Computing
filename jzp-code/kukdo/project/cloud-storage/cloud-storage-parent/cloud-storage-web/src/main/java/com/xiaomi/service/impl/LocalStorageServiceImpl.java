package com.xiaomi.service.impl;

import com.xiaomi.service.StorageService;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service("LocalStorage")
public class LocalStorageServiceImpl implements StorageService {

    private final Path rootLocation = Paths.get("upload_file");

    @Override
    public String store(MultipartFile file) {
        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }
            Path destinationFile = rootLocation.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
                throw new IllegalStateException("Cannot store file outside current directory.");
            }
            if (Files.exists(destinationFile)) {
                throw new RuntimeException("File already exists: " + destinationFile);
            }
            Files.copy(file.getInputStream(), destinationFile);
            return destinationFile.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file.", e);
        }

    }

    @Override
    public Resource loadAsResource(String fileName) {
        return null;
    }

    @Override
    public void delete(String filePath) {

    }

    @Override
    public void rename(String oldFileName, String newFileName) {

    }
}
