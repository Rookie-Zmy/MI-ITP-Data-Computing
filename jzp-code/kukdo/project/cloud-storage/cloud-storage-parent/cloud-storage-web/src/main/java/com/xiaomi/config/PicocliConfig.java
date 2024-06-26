package com.xiaomi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import picocli.CommandLine;

@Configuration
public class PicocliConfig {

    @Bean
    public CommandLine.IFactory picocliFactory() {
        return CommandLine.defaultFactory();
    }
}
