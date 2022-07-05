package com.epam.spring.homework2;

import com.epam.spring.homework2.beans.BeanD;
import com.epam.spring.homework2.config.FirstConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

public class Application {
  public static void main(String[] args) {
    System.out.println("-------INIT BEANS-------");
    ApplicationContext context = new AnnotationConfigApplicationContext(FirstConfig.class);
    System.out.println("-------CHECK BPP WORK-------");
    System.out.println(context.getBean("beanD", BeanD.class));
    System.out.println("-------ALL BEANS-------");
    Stream.of(context.getBeanDefinitionNames()).forEach(System.out::println);
    System.out.println("-------DESTROY BEANS-------");
    ((ConfigurableApplicationContext) context).close();
  }
}
