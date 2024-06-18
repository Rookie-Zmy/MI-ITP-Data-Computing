package com.xiaomi.dao;

import jakarta.persistence.*;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "file")
public class File {
    @Id
    @Column(name = "fileId")
    @GeneratedValue(strategy = IDENTITY)
    private Long fileId;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "uploadTime")
    private String uploadTime;

    @Column(name = "fileType")
    private String fileType;

    @Column(name = "currentFolderId")
    private Long currentFolderId;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getCurrentFolderId() {
        return currentFolderId;
    }

    public void setCurrentFolderId(Long currentFolderId) {
        this.currentFolderId = currentFolderId;
    }
}
