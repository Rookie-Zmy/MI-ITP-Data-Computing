package com.xiaomi.model;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableConfigurationProperties(AliyunOssProperties.class)
public class OssClientConfig {

    @Autowired
    private AliyunOssProperties aliyunOssProperties;

    @Bean
    public OSS ossClient() {
        ClientConfiguration conf = new ClientConfiguration();
        return new OSSClientBuilder().build(
                aliyunOssProperties.getEndpoint(),
                aliyunOssProperties.getAccessKeyId(),
                aliyunOssProperties.getAccessKeySecret());
    }

}
