package com.xiaomi.client;

import com.xiaomi.client.command.*;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import java.util.Scanner;

@Component
@CommandLine.Command(name = "cloudstorage", mixinStandardHelpOptions = true, version = "0.3.2",
        description = "Client for interacting with storage service",
        subcommands = {
                UploadCommand.class,
                DownloadCommand.class,
                DeleteCommand.class,
                ListCommand.class,
                RenameCommand.class
        })
public class CloudStorageMainClient implements Runnable {

    public static void main(String[] args) {
        new CommandLine(new CloudStorageMainClient()).execute(args);
        listenForExitCommand();
    }

    @Override
    public void run() {
        try {
            CommandLine.usage(this, System.out);
        } catch (Exception e) {
            throw new RuntimeException("System error, please re-run the program. " + e.getMessage());
        }
    }

    private static void listenForExitCommand() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("*************************************************");
            System.out.print("Enter command (type `exit` for exitApplication): ");
            String input = scanner.nextLine();
            String[] inputArgs = input.split(" ");
            if ("exit".equalsIgnoreCase(input.trim())) {
                System.exit(0);
            }
            CommandLine commandLine = new CommandLine(new CloudStorageMainClient());
            commandLine.execute(inputArgs);
        }
    }
}
