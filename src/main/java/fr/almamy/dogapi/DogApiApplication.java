package fr.almamy.dogapi;

import fr.almamy.dogapi.entity.Joke;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@SpringBootApplication
@Slf4j
public class DogApiApplication {
    //\\ Constants \\//
    private static final String URL = "https://official-joke-api.appspot.com/random_joke";

    //\\ Entry Point \\//
    public static void main(String[] args) {
        SpringApplication.run(DogApiApplication.class, args);
    }

    //\\ Bean Methods \\//
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Bean
    public CommandLineRunner run (RestTemplate restTemplate) {
        return args -> {
            Optional<Joke> joke = Optional.ofNullable(restTemplate.getForObject(URL, Joke.class));
            if (joke.isPresent()){
                log.info("Setup: {}", joke.get().getSetup());
                log.info("Punchline: {}", joke.get().getPunchline());
            }
        };
    }

}
