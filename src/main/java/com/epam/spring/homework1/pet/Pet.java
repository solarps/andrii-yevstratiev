package com.epam.spring.homework1.pet;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Pet {
  private final List<Animal> collection;

  public Pet(List<Animal> collection) {
    this.collection = collection;
  }

  public void printPets() {
    collection.stream()
        .map(Animal::getClass)
        .map(Class::getSimpleName)
        .forEach(System.out::println);
  }
}
