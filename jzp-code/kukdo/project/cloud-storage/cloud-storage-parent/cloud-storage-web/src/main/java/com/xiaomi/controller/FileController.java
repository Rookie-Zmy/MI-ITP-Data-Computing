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
        List<String> files = fileService.getAllFiles();
        files.forEach(System.out::println);
        return files;
    }

    @PostMapping("/files")
    public ResponseEntity<FileMetadata> uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println("Received file: " + file.getOriginalFilename());
        FileMetadata metadata = fileService.storeFile(file);
        return ResponseEntity.ok(metadata);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        FileMetadata metadata = fileService.getFileMetadata(id);
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

    @DeleteMapping("/files/{id}")
    public ResponseEntity<ApiResponse> deleteFile(@PathVariable long id) {
        fileService.deleteFile(id);
        return ResponseEntity.ok(new ApiResponse(true, "File deleted successfully"));
    }

    @PutMapping("/files/{id}")
    public ResponseEntity<ApiResponse> renameFile(@PathVariable long id, @RequestParam("newName") String newName) {
        fileService.renameFile(id, newName);
        return ResponseEntity.ok(new ApiResponse(true, "File renamed successfully"));
    }

}
