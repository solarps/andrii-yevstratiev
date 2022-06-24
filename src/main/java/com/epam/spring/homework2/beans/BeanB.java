package com.epam.spring.homework2.beans;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:dev.properties")
public class BeanB extends AbstractBean {

    public BeanB() {
    }

    public BeanB(String name, String value) {
        super(name, value);
    }

    private void initBean() {
        System.out.println("BeanB custom init");
    }

    private void destroyBean() {
        System.out.println("BeanB custom destroy");
    }
}
