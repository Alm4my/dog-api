package fr.almamy.dogapi.web;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DogControllerIntegrationTest {
    //\\ Constants \\//
    public static String HOST = "http://localhost:";

    //\\ Fields \\//
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private WebTestClient client;

    //\\ Tests \\//
    @Test
    public void checkRequireAuth(){
        client.get()
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }

    @Test
    public void getAllDogs(){
        WebTestClient
                .bindToServer()
                .baseUrl(HOST + port)
                .build()
                .get()
                .uri("/dogs")
                .header("Authorization", "Basic " + Base64Utils.encodeToString(("admin" + ":" + "password").getBytes(UTF_8)))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json");
    }

    @Test
    public void getBreedById(){
        ResponseEntity<String> response = restTemplate.getForEntity(HOST + port + "/1/breed", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void getAllBreeds(){
        client.get()
                .uri("/dogs/breed")
                .headers((header -> header.setBasicAuth("admin", "password")))
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void getAllDogNames(){
        client
                .get()
                .uri("/dogs/name")
                .header("Authorization", "Basic " + Base64Utils.encodeToString(("admin" + ":" + "password").getBytes(UTF_8)))
                .exchange()
                .expectStatus()
                .isOk();
    }

}
