package com.xiaomi;

// import com.xiaomi.client.CloudStorageClient;
import com.xiaomi.client.CloudStorageMainClient;
import com.xiaomi.config.AliyunOssProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootApplication
@EnableConfigurationProperties(AliyunOssProperties.class)
public class CloudStorageApplication {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("***WELCOME to CLOUD-STORAGE SYSTEM***");
        System.out.println("    Author: Kukdo");
        System.out.println("    Version: 0.3");
        System.out.println("----------------Login Page----------------");
        System.out.print("Please choose your storage type: `local` or `aliyun`: ");
        String type = scanner.nextLine();
        try {
            if ("aliyun".equalsIgnoreCase(type.trim())) {
                System.setProperty("file.service-type", type);
                System.out.println("----------------Aliyun Config----------------");
                System.out.println("Please set up your Aliyun OSS configs.");

                System.out.print("Please enter your accessKeyId: ");
                String accessKeyId = scanner.nextLine();

                System.out.print("Please enter your accessKeySecret: ");
                String accessKeySecret = scanner.nextLine();

                System.out.print("Please enter your bucketName: ");
                String bucketName = scanner.nextLine();

                if (!accessKeyId.isEmpty() && !accessKeySecret.isEmpty() && !bucketName.isEmpty()) {
                    System.setProperty("aliyun.oss.accessKeyId", accessKeyId);
                    System.setProperty("aliyun.oss.accessKeySecret", accessKeySecret);
                    System.setProperty(" aliyun.oss.bucketName", bucketName);
                } else {
                    System.out.println("Request failed, please re-run the program and check the configs or using `local` storage.");
                }
            } else if ("local".equalsIgnoreCase(type.trim())) {
                System.setProperty("file.service-type", type);
                System.out.println("----------------Local Config----------------");
                System.out.println("Please set up your local configs.");

                System.out.print("Please enter your database username: ");
                String dbUsername = scanner.nextLine();

                System.out.print("Please enter your database password: ");
                String dbPassword = scanner.nextLine();

                System.out.print("Please enter your current directory: ");
                String dir = scanner.nextLine();

                if (!dir.isEmpty() && !dbUsername.isEmpty() && !dbPassword.isEmpty()) {
                    System.setProperty("file.upload-dir", dir);
                    System.setProperty("spring.datasource.username", dbUsername);
                    System.setProperty("spring.datasource.password", dbPassword);
                } else {
                    System.out.println("Using default path (current directory): " + System.getProperty("user.dir"));
                    System.out.println("Using default db configs (username:root, password:123456)");
                    System.setProperty("file.upload-dir", System.getProperty("user.dir"));
                    System.out.println("---------------------------------------------------------");
                }
            } else {
                System.out.println("Wrong choice, please retry.");
                throw new RuntimeException("Wrong Choice.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Your storage type is not correct, please re-run the program." + e.getMessage());
        }

        context = SpringApplication.run(CloudStorageApplication.class, args);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(CloudStorageApplication::listenForExitCommand);
    }

    private static void listenForExitCommand() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("*************************************************");
            System.out.print("Enter command (type `exit` for exitApplication): ");
            String input = scanner.nextLine();
            String[] inputArgs = input.split(" ");
            if ("exit".equalsIgnoreCase(input.trim())) {
                exitApplication();
            }
            CommandLine commandLine = new CommandLine(new CloudStorageMainClient());
            commandLine.execute(inputArgs);
        }
    }

    private static void exitApplication() {
        int exitCode = SpringApplication.exit(context);
        System.exit(exitCode);
    }
}
