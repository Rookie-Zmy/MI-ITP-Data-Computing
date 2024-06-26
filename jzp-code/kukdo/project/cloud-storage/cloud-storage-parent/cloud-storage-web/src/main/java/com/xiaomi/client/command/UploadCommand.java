package com.xiaomi.client.command;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


@Command(name = "upload", description = "Upload file to the storage")
public class UploadCommand implements Runnable {
    @Option(names = {"--name", "-n"}, description = "Name of the file to upload", required = true)
    private String fileName;

    @Option(names = {"--source", "-s"}, description = "Path of the file to upload", required = true)
    private String filePath;

    @Override
    public void run() {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String baseUrl = "http://localhost:8080/api/files";
            HttpPost request = new HttpPost(baseUrl);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            if (!filePath.endsWith("/")) {
                filePath += "/";
            }
            String uploadFile = filePath + fileName;
            File file = new File(Paths.get(uploadFile).toUri()); // newly added
            builder.addBinaryBody("file", file);
            request.setEntity(builder.build());

            try (var response = client.execute(request)) {
                System.out.println(EntityUtils.toString(response.getEntity()));
            } catch (IOException | ParseException e) {
                throw new RuntimeException("Upload file failed, please re-check your option and args. " + e.getMessage());
            }
        } catch (IOException e) {
            throw new RuntimeException("Upload request failed. " + e.getMessage());
        }
    }

}
