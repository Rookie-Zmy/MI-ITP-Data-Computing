package com.xiaomi.client;

import com.xiaomi.client.command.*;

import org.springframework.stereotype.Component;
import picocli.CommandLine;

@Component
@CommandLine.Command(name = "cloudstorage", mixinStandardHelpOptions = true, version = "0.3.1",
        description = "Client for interacting with storage service",
        subcommands = {
                UploadCommand.class,
                DownloadCommand.class,
                DeleteCommand.class,
                ListCommand.class,
                RenameCommand.class
        })
public class CloudStorageMainClient implements Runnable {

    @Override
    public void run() {
        try {
            CommandLine.usage(this, System.out);
        } catch (Exception e) {
            throw new RuntimeException("System error, please re-run the program. " + e.getMessage());
        }
    }
}
