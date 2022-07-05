package com.epam.spring.homework2.beans;

import java.util.StringJoiner;

public abstract class AbstractBean {
  private String name;
  private Integer value;

  public AbstractBean() {}

  public AbstractBean(String name, Integer value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("value=" + value)
        .toString();
  }
}
