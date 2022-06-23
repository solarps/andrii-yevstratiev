package com.epam.spring.homework1.pet;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Pet {
    List<Animal> collection;

    public Pet(List<Animal> collection) {
        this.collection = collection;
    }

    public void printPets() {
        collection.forEach(animal -> System.out.println(animal.getClass().getSimpleName()));
    }
}
