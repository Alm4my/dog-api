package fr.almamy.dogapi.service;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Dog not found")
@NoArgsConstructor
public class DogNotFoundException extends RuntimeException{

}
