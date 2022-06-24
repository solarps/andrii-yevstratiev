package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanB;
import com.epam.spring.homework2.beans.BeanC;
import com.epam.spring.homework2.beans.BeanD;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.epam.spring.homework2.beans")
@Import(SecondConfig.class)
@PropertySource("classpath:dev.properties")
public class FirstConfig {

    @Bean(initMethod = "initBean", destroyMethod = "destroyBean")
    public BeanD beanD(@Value("${beanD.name}") String name, @Value("${beanD.value}") String val) {
        return new BeanD(name, val);
    }

    @Bean(initMethod = "initBean", destroyMethod = "destroyBean")
    @DependsOn("beanD")
    public BeanB beanB(@Value("${beanB.name}") String name, @Value("${beanB.value}") String val) {
        return new BeanB(name, val);
    }

    @Bean(initMethod = "initBean", destroyMethod = "destroyBean")
    @DependsOn("beanB")
    public BeanC beanC(@Value("${beanC.name}") String name, @Value("${beanC.value}") String val) {
        return new BeanC(name, val);
    }
}