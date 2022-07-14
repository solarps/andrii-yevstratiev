package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanC extends AbstractBean {
  public BeanC() {}

  public BeanC(String name, Integer val) {
    super(name, val);
  }

  private void initBean() {
    System.out.println("BeanC custom init");
  }

  private void destroyBean() {
    System.out.println("BeanC custom destroy");
  }
}
