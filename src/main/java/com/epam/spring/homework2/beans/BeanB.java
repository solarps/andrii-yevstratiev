package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;


@Component
public class BeanB extends AbstractBean {

    public BeanB() {
    }

    public BeanB(String name, Integer value) {
        super(name, value);
    }

    private void initBean() {
        System.out.println("BeanB custom init");
    }

    private void destroyBean() {
        System.out.println("BeanB custom destroy");
    }

    public void anotherInitBean() {
        System.out.println("BeanB another init");
    }
}
