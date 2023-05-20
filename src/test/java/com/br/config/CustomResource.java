package com.br.config;

import io.quarkus.test.common.DevServicesContext;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;
import java.util.Optional;

public class CustomResource implements QuarkusTestResourceLifecycleManager, DevServicesContext.ContextAware {

    private Optional<String> containerNetworkId;
    private GenericContainer container;

    @Override
    public void setIntegrationTestContext(DevServicesContext context) {
        containerNetworkId = context.containerNetworkId();
    }

    @Override
    public Map<String, String> start() {
        container = new GenericContainer(DockerImageName.parse("redis:latest")).withExposedPorts(6379);
        containerNetworkId.ifPresent(container::withNetworkMode);
        container.start();

        String host = "redis://" + container.getHost() + ":" + container.getMappedPort(6379);
        // return a map containing the configuration the application needs to use the service
        return ImmutableMap.of(
                "quarkus.redis.hosts", host);
    }


    @Override
    public void stop() {
        // close container
    }
}
