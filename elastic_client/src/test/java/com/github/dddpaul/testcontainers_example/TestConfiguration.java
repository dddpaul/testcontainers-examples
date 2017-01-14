package com.github.dddpaul.testcontainers_example;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.NodeBuilder;
import org.junit.ClassRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.testcontainers.containers.GenericContainer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

@Configuration
public class TestConfiguration {

    private static final String ELASTIC_IMAGE = "elasticsearch:5.1.2";
    private static final int ELASTIC_PORT = 9300;

    @ClassRule
    public static GenericContainer elastic = StringUtils.isEmpty(System.getenv("DOCKER_SOCK_BOUND")) ?
            new GenericContainer(ELASTIC_IMAGE).withExposedPorts(ELASTIC_PORT) :
            new SiblingContainer(ELASTIC_IMAGE).withExposedPorts(ELASTIC_PORT);

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

    private InetSocketAddress address() throws UnknownHostException {
        return new InetSocketAddress(elastic.getContainerIpAddress(), elastic.getMappedPort(ELASTIC_PORT));
    }
}
