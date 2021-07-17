package fr.almamy.dogapi.service;

import fr.almamy.dogapi.entity.Dog;
import fr.almamy.dogapi.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return dogRepository.findBreedById(id);
    }

    public List<String> retrieveDogNames(){
        return dogRepository.findAllName();
    }
}
