package com.sb.staging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.sb")
public class SbStagingApplication {
    public static void main(String[] args) {
        new SpringApplication(SbStagingApplication.class).run();
    }
}
