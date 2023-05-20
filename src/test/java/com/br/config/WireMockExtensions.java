package com.br.config;

import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockExtensions implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();

        wireMockServer.stubFor(get(urlEqualTo("/extensions"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                                "{" +
                                        "\"key\": \"io.quarkus:quarkus-rest-client\""+
                                "}"
                        )));

        wireMockServer.stubFor(get(urlMatching(".*")).atPriority(10).willReturn(aResponse()));

        return Map.of("quarkus.rest-client.extension.url", wireMockServer.baseUrl());
    }

    @Override
    public void stop() {
        if (null != wireMockServer) {
            wireMockServer.stop();
        }
    }
}