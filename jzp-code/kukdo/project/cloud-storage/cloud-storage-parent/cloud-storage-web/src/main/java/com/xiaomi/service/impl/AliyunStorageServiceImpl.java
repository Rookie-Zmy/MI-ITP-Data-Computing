package com.xiaomi.service.impl;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.xiaomi.service.StorageService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service("AliyunStorage")
public class AliyunStorageServiceImpl implements StorageService {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accesKeySecret;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    private OSS getOSSClient() {
        ClientConfiguration conf = new ClientConfiguration();
//        conf.setConnectionTimeout(5000);
//        conf.setSocketTimeout(10000);
//        conf.setMaxErrorRetry(3);
        return new OSSClientBuilder().build(endpoint, accessKeyId, accesKeySecret);
    }

    @Override
    public String store(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try (InputStream inputStream = file.getInputStream()) {
            OSS ossClient = getOSSClient();
            ossClient.putObject(bucketName, fileName, inputStream);
            ossClient.shutdown();
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file to OSS", e);
        }
        return fileName;
    }

    @Override
    public Resource loadAsResource(String fileName) {
        OSS ossClient = getOSSClient();
        try {
            InputStream inputStream = ossClient.getObject(bucketName, fileName).getObjectContent();
            return new InputStreamResource(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to download file from OSS", e);
        }
//        finally {
//            ossClient.shutdown();
//        }
    }

    @Override
    public void delete(String fileName) {
        OSS ossClient = getOSSClient();
        try {
            ossClient.deleteObject(bucketName, fileName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete file from OSS", e);
        } finally {
            ossClient.shutdown();
        }
    }

    @Override
    public void rename(String oldFileName, String newFileName) {
        OSS ossClient = getOSSClient();
        try {
            ossClient.copyObject(bucketName, oldFileName, bucketName, newFileName);
            ossClient.deleteObject(bucketName, oldFileName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to rename file on OSS", e);
        } finally {
            ossClient.shutdown();
        }
    }
}