package com.epam.spring.homework2.beans;



import java.util.StringJoiner;

public abstract class AbstractBean {
    String name;
    String value;

    public AbstractBean() {
    }

    public AbstractBean(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AbstractBean.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("value='" + value + "'")
                .toString();
    }
}
