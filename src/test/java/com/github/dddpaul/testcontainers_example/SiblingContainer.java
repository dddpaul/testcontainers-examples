package com.github.dddpaul.testcontainers_example;

import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.shaded.com.github.dockerjava.api.DockerClient;
import org.testcontainers.shaded.com.github.dockerjava.api.command.InspectContainerResponse;

public class SiblingContainer<T extends SiblingContainer<T>> extends GenericContainer<T> {

    private String networkName = "bridge";

    public SiblingContainer(String dockerImageName) {
        super(dockerImageName);
    }

    @Override
    public String getContainerIpAddress() {
        DockerClient client = DockerClientFactory.instance().client();
        InspectContainerResponse response = client.inspectContainerCmd(getContainerId()).exec();
        return response
                .getNetworkSettings()
                .getNetworks()
                .get(networkName)
                .getIpAddress();
    }

    @Override
    public Integer getMappedPort(int originalPort) {
        return originalPort;
    }

    public T withNetworkName(String dockerNetworkName) {
        this.networkName = dockerNetworkName;
        return self();
    }
}
