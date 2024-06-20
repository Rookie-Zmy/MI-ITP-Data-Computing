package com.example.pandown.controller;

import com.example.pandown.dao.FileOperate;
import com.example.pandown.dto.FileAdd;
import com.example.pandown.dto.FileSelect;
import com.example.pandown.service.FileOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;


@RestController
//@RequestMapping("file")
public class FileOperateController {
    @Autowired
    private FileOperateService fileOperateService;

    @GetMapping("/file/{name}")
    public FileSelect getFileOperateByName(@PathVariable String name) {
        return fileOperateService.getFileOperateByName(name);
    }

    @GetMapping("/filelist")
    public List<FileOperate> getAllFiles() {
        return fileOperateService.getAllFiles();
    }

    @PostMapping("/upfile")
    public String addNewFile(@RequestBody FileAdd fileAdd){
        return fileOperateService.addNewFile(fileAdd);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFileByName(@RequestParam String name) {
        try {
            fileOperateService.deleteFileByName(name);
            return ResponseEntity.ok("File deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }


    }


    @GetMapping("/list")
    public List<String> listFiles(@RequestParam String directory) {

        return fileOperateService.listFiles(directory);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam String directory, @RequestParam MultipartFile file) {
        try {
            fileOperateService.uploadFile(directory, file.getOriginalFilename(), file.getInputStream());
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload file");
        }
    }

    @GetMapping("/download")
    public ResponseEntity<InputStream> downloadFile(@RequestParam String directory, @RequestParam String fileName) {
        try {
            InputStream fileStream = fileOperateService.downloadFile(directory, fileName);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(fileStream);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/repository/delete")
    public ResponseEntity<String> deleteRepositoryFile(@RequestParam String directory, @RequestParam String fileName) {
        try {
            fileOperateService.deleteRepositoryFile(directory, fileName);
            return ResponseEntity.ok("File and corresponding database record deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to delete file from repository");
        }
    }



}
