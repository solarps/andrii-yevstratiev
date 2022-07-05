package com.epam.spring.homework1;

import com.epam.spring.homework1.config.BeansConfig;
import com.epam.spring.homework1.pet.Cheetah;
import com.epam.spring.homework1.pet.Pet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);
    context.getBean(Pet.class).printPets();

    System.out.println(context.getBean("firstCheetah")); // inject за ім'ям

    System.out.println(context.getBean(Cheetah.class));
    // inject за типом, але обирає bean з анотацією primary
    // якщо не було б анотації primary, був би error
  }
}
