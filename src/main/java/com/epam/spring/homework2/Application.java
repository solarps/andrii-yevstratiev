package com.epam.spring.homework2;

import com.epam.spring.homework2.beans.AbstractBean;
import com.epam.spring.homework2.config.FirstConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Application {
  public static void main(String[] args) {
    System.out.println("-------INIT BEANS-------");
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(FirstConfig.class);
    System.out.println("-------CHECK BPP WORK-------");
    context.getBeansOfType(AbstractBean.class).entrySet().forEach(System.out::println);
    System.out.println("-------ALL BEAN DEFINITIONS-------");
    Arrays.stream(context.getBeanDefinitionNames())
        .map(context::getBeanDefinition)
        .forEach(System.out::println);
    System.out.println("-------DESTROY BEANS-------");
    context.close();
  }
}
