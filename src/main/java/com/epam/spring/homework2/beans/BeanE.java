package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BeanE extends AbstractBean {
  @PostConstruct
  public void init() {
    System.out.println("BeanE init by PostConstruct annotation");
  }

  @PreDestroy
  public void destroy() {
    System.out.println("BeanE destroy by PreDestroy annotation");
  }
}
