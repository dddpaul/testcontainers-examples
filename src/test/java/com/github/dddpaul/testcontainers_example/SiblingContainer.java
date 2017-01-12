package com.github.dddpaul.testcontainers_example;

import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.shaded.com.github.dockerjava.api.DockerClient;
import org.testcontainers.shaded.com.github.dockerjava.api.command.InspectContainerResponse;

public class SiblingContainer<T extends SiblingContainer<T>> extends GenericContainer<T> {

    public SiblingContainer(String dockerImageName) {
        super(dockerImageName);
    }

    @Override
    public String getContainerIpAddress() {
        DockerClient client = DockerClientFactory.instance().client();
        InspectContainerResponse response = client.inspectContainerCmd(getContainerId()).exec();
        return response.getNetworkSettings().getIpAddress();
    }

    @Override
    public Integer getMappedPort(int originalPort) {
        return originalPort;
    }
}
