package fr.almamy.dogapi.mutator;

import fr.almamy.dogapi.entity.Dog;
import fr.almamy.dogapi.exception.BreedNotFoundException;
import fr.almamy.dogapi.repository.DogRepository;
import fr.almamy.dogapi.exception.DogNotFoundException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class Mutation implements GraphQLMutationResolver {
    //\\ Fields \\//
    private final DogRepository dogRepository;

    //\\ Public Methods \\//
    public Dog newDog(String name, String breed, String origin){
        Dog dog = new Dog(name, breed, origin);
        dogRepository.save(dog);
        return dog;
    }

    public boolean deleteDogBreed(String breed){
        boolean deleted = false;
        Iterable<Dog> allDogs = dogRepository.findAll();
        /// Loop through all dogs to check their breed
        for (Dog d : allDogs)
            if (d.getBreed().equalsIgnoreCase(breed)){
                /// delete
                dogRepository.delete(d);
                /// We can add break here after delete to delete only the first occurrence of the breed
                deleted = true;
            }
        /// Throw exception if the breed doesn't exist
        if (!deleted)
            throw new BreedNotFoundException("Breed Not Found", breed);

        return true;
    }

    public Dog updateDogName(String newName, Long id){
        Optional<Dog> optional = dogRepository.findById(id);

        if (optional.isPresent()){
            Dog dog = optional.get();
            dog.setName(newName);
            dogRepository.save(dog);
            return dog;
        }

        throw new DogNotFoundException("Dog Not Found", id);
    }
}
