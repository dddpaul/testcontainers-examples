package com.github.dddpaul.testcontainers_example;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.junit.ClassRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.testcontainers.containers.GenericContainer;

import java.net.UnknownHostException;

@Configuration
public class TestConfiguration {

    private static final int mongoPort = 27017;

    @ClassRule
    public static GenericContainer mongo = new SiblingContainer("mvertes/alpine-mongo:3.2.10-3")
            .withExposedPorts(mongoPort);

    @Bean
    @Primary
    public MongoClient mongoClient() throws UnknownHostException {
        return new MongoClient(address());
    }

    private ServerAddress address() throws UnknownHostException {
        return new ServerAddress(mongo.getContainerIpAddress(), mongo.getMappedPort(mongoPort));
    }
}
