package com.xiaomi.controller;

import com.xiaomi.service.FileService;
import com.xiaomi.dao.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileController {

    // @Autowired
    // private FileService fileService;

    @PostMapping("/api/files")
    public Long uploadFile(@RequestBody File file) {
        return 0L;
    }

    @GetMapping("/api/files/{fileId}")
    public Long downloadFile(@PathVariable long fileId) {
        return 0L;
    }

    @DeleteMapping("/api/files/{fileId}")
    public Long deleteFile(@PathVariable long fileId) {
        return 0L;
    }

    @PutMapping("/api/files/{fileId}")
    public Long renameFile(@PathVariable long fileId) {
        return 0L;
    }

}
