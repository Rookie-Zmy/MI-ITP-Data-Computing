package com.xiaomi.client.command;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Command(name = "rename", description = "Rename file in the storage by id")
public class RenameCommand implements Runnable {
    @CommandLine.Option(names = {"--id"}, description = "ID of the file to rename")
    private Long renameFileId;

    @CommandLine.Option(names = {"--name", "-n"}, description = "Name of the renamed file", required = true)
    private String fileName;

    @Override
    public void run() {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String baseUrl = "http://localhost:8080/api/files";
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
        } catch (IOException e) {
            throw new RuntimeException("List request failed. " + e.getMessage());
        }
    }
}
