package com.xiaomi.client.command;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import picocli.CommandLine.Command;

import java.io.IOException;


@Command(name = "list", description = "List all files in the storage")
public class ListCommand implements Runnable {

    @Override
    public void run() {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String baseUrl = "http://localhost:8080/api/files";
            HttpGet request = new HttpGet(baseUrl);

            try (var response = client.execute(request)) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                System.out.println("Files:" + jsonResponse);
                System.out.println("File listed successfully.");
            } catch (IOException | ParseException e) {
                throw new RuntimeException("List file failed, please re-check your option and args. " + e.getMessage());
            }
        } catch (IOException e) {
            throw new RuntimeException("List request failed. " + e.getMessage());
        }
    }
}
