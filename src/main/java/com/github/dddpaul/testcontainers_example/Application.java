package com.github.dddpaul.testcontainers_example;

import com.mongodb.MongoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Bean
    MongoClient mongoClient() {
        return new MongoClient("localhost");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
