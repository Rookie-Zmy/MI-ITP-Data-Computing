package com.xiaomi.client;

import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
@CommandLine.Command(name = "cloudstorage-original", mixinStandardHelpOptions = true, version = "0.3",
        description = "Client for interacting with storage service")
public class CloudStorageClient implements Runnable {

    @Option(names = {"-u", "--upload"}, description = "Upload file to the storage")
    private String uploadFile;

    @Option(names = {"-d", "--download"}, description = "Download file from the storage by id")
    private Long downloadFileId;

    @Option(names = {"-p", "--path"}, description = "Path to save the downloaded file")
    private String downloadPath;

    @Option(names = {"-n", "--name"}, description = "Name of the downloaded file")
    private String fileName;

    @Option(names = {"-r", "--rename"}, description = "Rename file in the storage by id")
    private Long renameFileId;

    @Option(names = {"-del", "--delete"}, description = "Delete file from the storage by id")
    private Long deleteFileId;

    @Option(names = {"-l", "--list"}, description = "List all files in the storage")
    private boolean listFiles;

    private final String baseUrl = "http://localhost:8080/api/files";

    @Override
    public void run() {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            if (uploadFile != null) {
                uploadFile(client);
            } else if (downloadFileId != null) {
                downloadFile(client);
            } else if (renameFileId != null) {
                renameFile(client);
            } else if (deleteFileId != null) {
                deleteFile(client);
            } else if (listFiles) {
                listFiles(client);
            } else {
                System.out.println("No valid option provided. Use --help to see the available options.");
            }
        } catch (Exception e) {
            throw new RuntimeException("System error, please re-run the program. " + e.getMessage());
        }
    }

    private void uploadFile(CloseableHttpClient client) throws IOException {
        HttpPost request = new HttpPost(baseUrl);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        File file = new File(Paths.get(uploadFile).toUri()); // newly added
        builder.addBinaryBody("file", file);
        // builder.addBinaryBody("file", Files.readAllBytes(Paths.get(uploadFile))); // bug code
        request.setEntity(builder.build());

        try (var response = client.execute(request)) {
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (ParseException e) {
            throw new RuntimeException("Upload file failed, please re-check your option and args. " + e.getMessage());
        }
    }

    private void downloadFile(CloseableHttpClient client) {
        HttpGet request = new HttpGet(baseUrl + "/" + downloadFileId);

        try (var response = client.execute(request)) {
            Path downloadFilePath;
            if (downloadPath != null) {
                downloadFilePath = Paths.get(downloadPath);
            } else {
                downloadFilePath = Paths.get(System.getProperty("user.dir"));
            }
            if (fileName != null) {
                downloadFilePath = downloadFilePath.resolve(fileName);
            } else {
                throw new IOException("The name of the file is not given, please use -n/--name to retry.");
            }
            Files.write(downloadFilePath, EntityUtils.toByteArray(response.getEntity()));
            System.out.println("File downloaded successfully.");
        } catch (IOException e) {
            throw new RuntimeException("Download file failed, please re-check your option and args. " + e.getMessage());
        }
    }

    private void renameFile(CloseableHttpClient client) throws IOException {
        HttpPut request = new HttpPut(baseUrl + "/" + renameFileId);
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("newName", fileName));
        request.setEntity(new UrlEncodedFormEntity(params));

        try (var response = client.execute(request)) {
            System.out.println(EntityUtils.toString(response.getEntity()));
            System.out.println("File renamed successfully.");
        } catch (ParseException e) {
            throw new RuntimeException("Rename file failed, please re-check your option and args. " + e.getMessage());
        }
    }

    private void deleteFile(CloseableHttpClient client) {
        HttpDelete request = new HttpDelete(baseUrl + "/" + deleteFileId);

        try (var response = client.execute(request)) {
            System.out.println(EntityUtils.toString(response.getEntity()));
            System.out.println("File deleted successfully.");
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Delete file failed, please re-check your option and args. " + e.getMessage());
        }
    }

    private void listFiles(CloseableHttpClient client) {
        HttpGet request = new HttpGet(baseUrl);

        try (var response = client.execute(request)) {
            System.out.println("File listed successfully.");
        } catch (IOException e) {
            throw new RuntimeException("List file failed, please re-check your option and args. " + e.getMessage());
        }
    }
}
