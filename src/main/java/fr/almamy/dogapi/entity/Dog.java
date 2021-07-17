package fr.almamy.dogapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dog {
    /// Fields
    @Getter @Setter
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String breed;
    private String origin;

}
