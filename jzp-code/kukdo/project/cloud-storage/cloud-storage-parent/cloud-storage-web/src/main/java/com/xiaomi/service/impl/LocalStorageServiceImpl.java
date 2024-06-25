package com.xiaomi.service.impl;

import com.xiaomi.service.StorageService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Service("LocalStorage")
public class LocalStorageServiceImpl implements StorageService {

    private final Path rootLocation;

    public LocalStorageServiceImpl(@Value("${file.upload-dir}") String uploadDir) {
        this.rootLocation = Paths.get(uploadDir);
    }

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
    public List<String> listFiles() {
        List<String> fileNames = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(rootLocation)) {
            for (Path path : directoryStream) {
                if (Files.isRegularFile(path)) {
                    fileNames.add(path.getFileName().toString());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to list files", e);
        }
        return fileNames;
    }

    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path filePath = rootLocation.resolve(fileName).normalize();
            Resource resource = new FileSystemResource(filePath.toFile());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + fileName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load file", e);
        }
    }

    @Override
    public void delete(String filePath) {
        try {
            Path deletePath = rootLocation.resolve(filePath).normalize();
            Files.deleteIfExists(deletePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete file", e);
        }
    }

    @Override
    public void rename(String oldFileName, String newFileName) {
        try {
            Path source = rootLocation.resolve(oldFileName).normalize();
            Path target = rootLocation.resolve(newFileName).normalize();
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("Failed to rename file", e);
        }
    }

}
