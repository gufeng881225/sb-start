package com.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.sb")
public class SbApplication {

    public static void main(String[] args) {
        new SpringApplication(SbApplication.class).run();
    }
}
