package fr.almamy.dogapi.web;

import fr.almamy.dogapi.entity.Dog;
import fr.almamy.dogapi.service.DogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DogController {
    //\\ Fields \\//
    private final DogService dogService;

    //\\ Public Methods \\//
    @GetMapping("/dogs")
    public ResponseEntity<List<Dog>> getAllDogs(){
        var list = dogService.retrieveDogs();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/dogs/breed")
    public ResponseEntity<List<String>> getDogBreeds(){
        var list = dogService.retrieveDogBreed();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}/breed")
    public ResponseEntity<String> getBreedById(@PathVariable Long id){
        var breed = dogService.retrieveDogBreedById(id);
        return new ResponseEntity<>(breed, HttpStatus.OK);
    }

    @GetMapping("/dogs/name")
    public ResponseEntity<List<String>> getDogNames(){
        var list = dogService.retrieveDogNames();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
