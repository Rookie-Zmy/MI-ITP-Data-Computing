package com.xiaomi.service.impl;

import com.xiaomi.model.FileMetadata;
import com.xiaomi.repository.FileMetadataRepository;
import com.xiaomi.service.FileService;
import com.xiaomi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
@ConditionalOnProperty(name = "file.service-type", havingValue = "local")
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
    public List<String> getAllFiles() {
        return storageService.listFiles();
    }

    @Override
    public FileMetadata getFileMetadata(Long Id) {
        return fileMetadataRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("File not found with Id " + Id));
    }

    @Override
    public Resource fetchFile(String filePath) {
        return storageService.loadAsResource(filePath);
    }

    @Override
    public void deleteFile(Long Id) {
        FileMetadata metadata = getFileMetadata(Id);
        try {
            storageService.delete(metadata.getFilePath());
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
            storageService.rename(oldFilePath.toString(), newFilePath.toString());
            metadata.setFileName(newName);
            metadata.setFilePath(newFilePath.toString());
            fileMetadataRepository.save(metadata);
        } catch (Exception e) {
            throw new RuntimeException("Failed to rename file: " + e.getMessage());
        }
    }

}
