package fr.almamy.dogapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Dog {
    //\\ Fields \\//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;
    private String origin;

    //\\ Constructor \\//
    public Dog(String name, String breed, String origin){
        this.name = name;
        this.breed = breed;
        this.origin = origin;
    }


}
