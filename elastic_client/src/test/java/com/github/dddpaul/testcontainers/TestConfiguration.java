package com.github.dddpaul.testcontainers;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.NodeBuilder;
import org.junit.ClassRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.testcontainers.containers.GenericContainer;

import java.net.UnknownHostException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
public class TestConfiguration {

    private static final String ELASTIC_IMAGE = "elasticsearch:5.1.2";
    private static final int ELASTIC_PORT = 9300;

    @ClassRule
    public static GenericContainer elastic = new GenericContainer(ELASTIC_IMAGE)
            .withExposedPorts(ELASTIC_PORT)
            .withStartupTimeout(Duration.of(120, ChronoUnit.SECONDS));

    @Bean
    @Primary
    public Client elasticsearchClient() throws UnknownHostException {
        Settings settings = Settings.settingsBuilder()
                .put("path.home", System.getProperty("user.dir"))
                .build();
        return NodeBuilder.nodeBuilder()
                .settings(settings)
                .node()
                .client();
    }
}