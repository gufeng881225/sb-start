package com.sb.staging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.sb")
public class SbStagingApplication {
    public static void main(String[] args) {
        new SpringApplication(SbStagingApplication.class).run();
    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("path_route", r -> r.path("/csdn")
//                        .uri("https://blog.csdn.net"))
//                .build();
//    }
}
