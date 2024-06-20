package com.xiaomi.service.impl;

import com.xiaomi.model.FileMetadata;
import com.xiaomi.repository.FileMetadataRepository;
import com.xiaomi.service.FileService;
import com.xiaomi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@Service("Local")
public class LocalFileServiceImpl implements FileService {

    @Autowired
    @Qualifier("LocalStorage")
    private StorageService storageService;

    @Autowired
    private FileMetadataRepository fileMetadataRepository;

    @Override
    public FileMetadata storeFile(MultipartFile file) {
        String filePath = storageService.store(file);
        FileMetadata metadata = new FileMetadata();
        metadata.setFileName(file.getOriginalFilename());
        metadata.setFilePath(filePath);
        metadata.setUploadTime(LocalDateTime.now());
        return fileMetadataRepository.save(metadata);
    }

    @Override
    public FileMetadata getFileMetadata(Long Id) {
        return fileMetadataRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("File not found with Id " + Id));
    }

    @Override
    public void deleteFile(Long Id) {
        FileMetadata metadata = getFileMetadata(Id);
        Path filePath = Paths.get(metadata.getFilePath());

        try {
            Files.deleteIfExists(filePath);
            fileMetadataRepository.delete(metadata);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete file: " + e.getMessage());
        }
    }

    @Override
    public void renameFile(Long id, String newName) {
        FileMetadata metadata = getFileMetadata(id);
        Path oldFilePath = Paths.get(metadata.getFilePath());
        Path newFilePath = oldFilePath.resolveSibling(newName);
        try {
            Files.move(oldFilePath, newFilePath, StandardCopyOption.REPLACE_EXISTING);
            metadata.setFileName(newName);
            metadata.setFilePath(newFilePath.toString());
            fileMetadataRepository.save(metadata);
        } catch (Exception e) {
            throw new RuntimeException("Failed to rename file: " + e.getMessage());
        }
    }

    @Override
    public Resource loadFileAsResource(String filePath) {
        return null;
    }
}
