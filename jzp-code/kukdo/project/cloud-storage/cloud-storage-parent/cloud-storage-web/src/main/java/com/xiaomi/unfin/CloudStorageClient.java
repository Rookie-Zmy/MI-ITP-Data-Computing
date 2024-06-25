package com.xiaomi.unfin;

import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Command(name = "cloudstorage", mixinStandardHelpOptions = true, version = "1.0",
        description = "Client for interacting with cloud storage service")
public class CloudStorageClient implements Runnable {

    @Option(names = {"-u", "--upload"}, description = "Upload file to cloud storage")
    private String uploadFile;

    @Option(names = {"-d", "--download"}, description = "Download file from cloud storage by ID")
    private Long downloadFileId;

    @Option(names = {"-r", "--rename"}, description = "Rename file in cloud storage by ID")
    private String[] renameFile;

    @Option(names = {"-del", "--delete"}, description = "Delete file from cloud storage by ID")
    private Long deleteFileId;

    @Option(names = {"-l", "--list"}, description = "List all files in cloud storage")
    private boolean listFiles;

    private final String baseUrl = "http://localhost:8080/api/files";

    public static void main(String[] args) {
        int exitCode = new CommandLine(new CloudStorageClient()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            if (uploadFile != null) {
                uploadFile(client);
            } else if (downloadFileId != null) {
                downloadFile(client);
            } else if (renameFile != null && renameFile.length == 2) {
                renameFile(client);
            } else if (deleteFileId != null) {
                deleteFile(client);
            } else if (listFiles) {
                listFiles(client);
            } else {
                System.out.println("No valid option provided. Use --help to see the available options.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void uploadFile(CloseableHttpClient client) throws IOException {
        HttpPost request = new HttpPost(baseUrl + "/upload");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("file", Files.readAllBytes(Paths.get(uploadFile)));
        request.setEntity(builder.build());

        try (var response = client.execute(request)) {
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void downloadFile(CloseableHttpClient client) {
        HttpGet request = new HttpGet(baseUrl + "/download/" + downloadFileId);

        try (var response = client.execute(request)) {
            Files.write(Paths.get("downloaded_file"), EntityUtils.toByteArray(response.getEntity()));
            System.out.println("File downloaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void renameFile(CloseableHttpClient client) throws IOException {
        HttpPost request = new HttpPost(baseUrl + "/rename/" + renameFile[0]);
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("newName", renameFile[1]));
        request.setEntity(new UrlEncodedFormEntity(params));

        try (var response = client.execute(request)) {
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void deleteFile(CloseableHttpClient client) {
        HttpDelete request = new HttpDelete(baseUrl + "/" + deleteFileId);

        try (var response = client.execute(request)) {
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void listFiles(CloseableHttpClient client) {
        HttpGet request = new HttpGet(baseUrl);

        try (var response = client.execute(request)) {
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
