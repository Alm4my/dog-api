package fr.almamy.dogapi.service;

import fr.almamy.dogapi.entity.Dog;
import fr.almamy.dogapi.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogService {
    //\\ Fields \\//
    @Autowired
    DogRepository dogRepository;

    //\\ Public Methods \\//
    public List<Dog> retrieveDogs(){
        return (List<Dog>) dogRepository.findAll();
    }

    public List<String> retrieveDogBreed(){
        return dogRepository.findAllBreed();
    }

    public String retrieveDogBreedById(Long id){
        Optional<String> optionalBreed = Optional.ofNullable(dogRepository.findBreedById(id));
        return optionalBreed.orElseThrow(DogNotFoundException::new);
    }

    public List<String> retrieveDogNames(){
        return dogRepository.findAllName();
    }
}
