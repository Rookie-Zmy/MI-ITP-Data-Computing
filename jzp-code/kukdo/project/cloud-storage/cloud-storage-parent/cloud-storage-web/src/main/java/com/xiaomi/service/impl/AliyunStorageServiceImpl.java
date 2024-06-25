package com.xiaomi.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.xiaomi.model.AliyunOssProperties;
import com.xiaomi.service.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service("AliyunStorage")
public class AliyunStorageServiceImpl implements StorageService {

    private final OSS ossClient;
    private final AliyunOssProperties aliyunOssProperties;

    @Autowired
    public AliyunStorageServiceImpl(OSS ossClient, AliyunOssProperties aliyunOssProperties) {
        this.ossClient = ossClient;
        this.aliyunOssProperties = aliyunOssProperties;
    }

    @Override
    public String store(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try (InputStream inputStream = file.getInputStream()) {
            ossClient.putObject(aliyunOssProperties.getBucketName(), fileName, inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file to OSS", e);
        }
        return fileName;
    }

    @Override
    public List<String> listFiles() {
        List<String> fileNames = new ArrayList<>();
        try {
            ObjectListing objectListing = ossClient.listObjects(new ListObjectsRequest(aliyunOssProperties.getBucketName()));
            for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                fileNames.add(objectSummary.getKey());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to list files from OSS", e);
        }
        return fileNames;
    }

    @Override
    public Resource loadAsResource(String fileName) {
        try {
            InputStream inputStream = ossClient.getObject(aliyunOssProperties.getBucketName(), fileName).getObjectContent();
            return new InputStreamResource(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to download file from OSS", e);
        }
    }

    @Override
    public void delete(String fileName) {
        try {
            ossClient.deleteObject(aliyunOssProperties.getBucketName(), fileName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete file from OSS", e);
        }
    }

    @Override
    public void rename(String oldFileName, String newFileName) {
        try {
            ossClient.copyObject(aliyunOssProperties.getBucketName(), oldFileName, aliyunOssProperties.getBucketName(), newFileName);
            ossClient.deleteObject(aliyunOssProperties.getBucketName(), oldFileName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to rename file on OSS", e);
        }
    }

}