package com.xiaomi;

import com.xiaomi.config.AliyunOssProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AliyunOssProperties.class)
public class CloudStorageApplication {

    public static void main(String[] args) {

        String type = System.getenv("FILE_SERVICE_TYPE");
        try {
            if ("aliyun".equalsIgnoreCase(type)) {
                System.setProperty("file.service-type", type);
                System.out.println("----------------Aliyun Config----------------");

                String endpoint = System.getenv("ALIYUN_OSS_ENDPOINT");
                String accessKeyId = System.getenv("ALIYUN_OSS_ACCESS_KEY_ID");
                String accessKeySecret = System.getenv("ALIYUN_OSS_ACCESS_KEY_SECRET");
                String bucketName = System.getenv("ALIYUN_OSS_BUCKET_NAME");

                if (accessKeyId != null && !accessKeyId.isEmpty() &&
                        accessKeySecret != null && !accessKeySecret.isEmpty() &&
                        bucketName != null && !bucketName.isEmpty() &&
                        endpoint != null && !endpoint.isEmpty()) {
                    System.setProperty("aliyun.oss.endpoint", endpoint);
                    System.setProperty("aliyun.oss.accessKeyId", accessKeyId);
                    System.setProperty("aliyun.oss.accessKeySecret", accessKeySecret);
                    System.setProperty("aliyun.oss.bucketName", bucketName);
                } else {
                    throw new IllegalArgumentException("Aliyun OSS configs are not properly set.");
                }
            } else if ("local".equalsIgnoreCase(type)) {
                System.setProperty("file.service-type", type);
                System.out.println("----------------Local Config----------------");

                String dbUsername = System.getenv("SPRING_DATASOURCE_USERNAME");
                String dbPassword = System.getenv("SPRING_DATASOURCE_PASSWORD");
                String dir = System.getenv("FILE_UPLOAD_DIR");

                if (dbUsername != null && !dbUsername.isEmpty() &&
                        dbPassword != null && !dbPassword.isEmpty() &&
                        dir != null && !dir.isEmpty()) {
                    System.setProperty("file.upload-dir", dir);
                    System.setProperty("spring.datasource.username", dbUsername);
                    System.setProperty("spring.datasource.password", dbPassword);
                } else {
                    System.out.println("Using default path (current directory): " + System.getProperty("user.dir"));
                    System.out.println("Using default db configs (username:root, password:123456)");
                    System.setProperty("file.upload-dir", System.getProperty("user.dir"));
                }
            } else {
                throw new IllegalArgumentException("Invalid storage type: " + type);
            }
        } catch (Exception e) {
            throw new RuntimeException("Your config is not correctly set, please re-run the program." + e.getMessage());
        }
        SpringApplication.run(CloudStorageApplication.class, args);
    }

}
