package com.github.dddpaul.testcontainers;

import com.mongodb.MongoClient;
import org.junit.ClassRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.testcontainers.containers.GenericContainer;

import java.net.UnknownHostException;

@Configuration
public class TestConfiguration {

    private static final String MONGO_IMAGE = "mvertes/alpine-mongo:3.2.10-3";
    private static final int MONGO_PORT = 27017;

    @ClassRule
    public static GenericContainer mongo = new GenericContainer(MONGO_IMAGE).withExposedPorts(MONGO_PORT);

    @Bean
    @Primary
    public MongoClient mongoClient() throws UnknownHostException {
        return new MongoClient(mongo.getContainerIpAddress(), mongo.getMappedPort(MONGO_PORT));
    }
}
