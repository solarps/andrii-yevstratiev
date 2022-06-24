package com.epam.spring.homework2;

import com.epam.spring.homework2.beans.BeanD;
import com.epam.spring.homework2.config.FirstConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;


public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(FirstConfig.class);
        System.out.println(context.getBean("beanD", BeanD.class));
        Stream.of(context.getBeanDefinitionNames()).forEach(System.out::println);
        ((ConfigurableApplicationContext) context).close();
    }
}
