package com.xiaomi.client.command;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Command(name = "download", description = "Download file from the storage by id")
public class DownloadCommand implements Runnable {
    @Option(names = {"--id"}, description = "ID of the file to download", required = true)
    private Long downloadFileId;

    @Option(names = {"--name", "-n"}, description = "Name of the downloaded file", required = true)
    private String fileName;

    @Option(names = {"--destination", "-d"}, description = "Path to save the download file", required = true)
    private String downloadPath;

    @Override
    public void run() {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String baseUrl = "http://localhost:8080/api/files";
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
                    throw new IOException("The name of the file is not given, please use --name to retry.");
                }
                Files.write(downloadFilePath, EntityUtils.toByteArray(response.getEntity()));
                System.out.println("File downloaded successfully.");
            } catch (IOException e) {
                throw new RuntimeException("Download file failed, please re-check your option and args. " + e.getMessage());
            }
        } catch (IOException e) {
            throw new RuntimeException("Download request failed. " + e.getMessage());
        }
    }
}
