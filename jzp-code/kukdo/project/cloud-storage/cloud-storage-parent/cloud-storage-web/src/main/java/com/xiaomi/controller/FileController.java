package com.xiaomi.controller;

import com.xiaomi.ApiResponse;
import com.xiaomi.model.FileMetadata;
import com.xiaomi.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequestMapping("/api")
@RestController
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/files")
    public List<String> listFiles() {
        return fileService.getAllFiles();
    }

    @PostMapping("/files")
    public ResponseEntity<FileMetadata> uploadFile(@RequestParam("file") MultipartFile file) {
        FileMetadata metadata = fileService.storeFile(file);
        return ResponseEntity.ok(metadata);
    }

    @GetMapping("/files/{Id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long Id) {
        FileMetadata metadata = fileService.getFileMetadata(Id);
        Path filePath = Paths.get(metadata.getFilePath());
        try {
            Resource resource = fileService.fetchFile(metadata.getFilePath());
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + metadata.getFileName() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("File not found or not readable" + metadata.getFilePath());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to download file: " + e.getMessage());
        }
    }

    @DeleteMapping("/files/{Id}")
    public ResponseEntity<ApiResponse> deleteFile(@PathVariable long Id) {
        fileService.deleteFile(Id);
        return ResponseEntity.ok(new ApiResponse(true, "File deleted successfully"));
    }

    @PutMapping("/files/{Id}")
    public ResponseEntity<ApiResponse> renameFile(@PathVariable long Id, @RequestParam("newName") String newName) {
        fileService.renameFile(Id, newName);
        return ResponseEntity.ok(new ApiResponse(true, "File renamed successfully"));
    }

}
