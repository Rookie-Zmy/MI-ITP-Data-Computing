package com.xiaomi.controller;

import com.xiaomi.dao.Folder;
import com.xiaomi.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FolderController {

    // @Autowired
    // private FolderService fileService;

    @PostMapping("/api/folders")
    public Long createFolder(@RequestBody Folder folder) {
        return 0L;
    }

    @GetMapping("/api/folders/{folderId}")
    public Long fetchFolder(@PathVariable long folderId) {
        return 0L;
    }

    @DeleteMapping("/api/folders/{folderId}")
    public Long deleteFolder(@PathVariable long folderId) {
        return 0L;
    }

    @PutMapping("/api/folders/{folderId}")
    public Long renameFolder(@PathVariable long folderId) {
        return 0L;
    }
}
