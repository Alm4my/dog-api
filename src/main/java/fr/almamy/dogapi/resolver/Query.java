package fr.almamy.dogapi.resolver;

import fr.almamy.dogapi.entity.Dog;
import fr.almamy.dogapi.repository.DogRepository;
import fr.almamy.dogapi.exception.DogNotFoundException;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class Query implements GraphQLQueryResolver {
    //\\ Fields \\//
    private final DogRepository dogRepository;

    //\\ Public Methods \\//
    public Iterable<Dog> findAllDogs(){
        return dogRepository.findAll();
    }

    public Dog findDogById(Long id){
        Optional<Dog> optional = dogRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new DogNotFoundException("Dog Not Found", id);
    }
}
