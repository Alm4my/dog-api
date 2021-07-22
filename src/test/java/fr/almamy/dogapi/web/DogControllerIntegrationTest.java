package fr.almamy.dogapi.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Base64Utils;

import static java.nio.charset.StandardCharsets.UTF_8;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DogControllerIntegrationTest {
    //\\ Constants \\//
    public static String HOST = "http://localhost:";

    //\\ Fields \\//
    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient client;

    //\\ Tests \\//
    @Test
    public void checkRequireAuth() {
        client.get()
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }

    @Test
    public void getAllDogs() {
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
    public void getBreedById() {
        client
                .get()
                .uri("/1/breed")
                .headers(httpHeaders -> httpHeaders.setBasicAuth("admin", "password"))
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.TEXT_PLAIN + ";charset=UTF-8")
                .expectBody();
    }

    @Test
    public void getAllBreeds() {
        client.get()
                .uri("/dogs/breed")
                .headers((header -> header.setBasicAuth("admin", "password")))
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void getAllDogNames() {
        client
                .get()
                .uri("/dogs/name")
                .header("Authorization", "Basic " + Base64Utils.encodeToString(("admin" + ":" + "password").getBytes(UTF_8)))
                .exchange()
                .expectStatus()
                .isOk();
    }

}
