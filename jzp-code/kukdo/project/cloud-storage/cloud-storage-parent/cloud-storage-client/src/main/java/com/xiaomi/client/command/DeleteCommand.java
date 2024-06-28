package com.xiaomi.client.command;

import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;

@Command(name = "delete", description = "Delete file from the storage by id")
public class DeleteCommand implements Runnable {
    @Option(names = {"--id"}, description = "ID of the file to delete", required = true)
    private Long deleteFileId;

    @Override
    public void run() {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String baseUrl = "http://localhost:8080/api/files";
            HttpDelete request = new HttpDelete(baseUrl + "/" + deleteFileId);

            try (var response = client.execute(request)) {
                System.out.println(EntityUtils.toString(response.getEntity()));
                System.out.println("File deleted successfully.");
            } catch (IOException | ParseException e) {
                throw new RuntimeException("Delete file failed, please re-check your option and args. " + e.getMessage());
            }
        } catch (IOException e) {
            throw new RuntimeException("Delete request failed. " + e.getMessage());
        }
    }
}
