package com.xiaomi.dao;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "folder")
public class Folder {
    @Id
    @Column(name = "folderId")
    @GeneratedValue(strategy = IDENTITY)
    private Long folderId;

    @Column(name = "folderName")
    private String folderName;

    @Column(name = "parentFolderId")
    private Long parentFolderId;

    private List<Long> fileIds;

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public Long getParentFolderId() {
        return parentFolderId;
    }

    public void setParentFolderId(Long parentFolderId) {
        this.parentFolderId = parentFolderId;
    }

    public List<Long> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<Long> fileIds) {
        this.fileIds = fileIds;
    }
}
