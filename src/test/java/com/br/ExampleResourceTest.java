package com.br;

import com.br.config.CustomResource;
import com.br.config.WireMockExtensions;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource.List({
        @QuarkusTestResource(WireMockExtensions.class),
        @QuarkusTestResource(CustomResource.class)})
public class ExampleResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/ola?cache=teste")
                .then()
                .statusCode(200)
                .body(is("[\"io.quarkus:quarkus-rest-client\",\"io.quarkus:quarkus-rest-client\",\"io.quarkus:quarkus-rest-client\",\"io.quarkus:quarkus-rest-client\",\"io.quarkus:quarkus-rest-client\",\"io.quarkus:quarkus-rest-client\"]"));
    }

}