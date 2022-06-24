package com.epam.spring.homework2.postprocessors;

import com.epam.spring.homework2.beans.AbstractBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanValidatorPostProcessor implements BeanPostProcessor {


    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AbstractBean) {
            validate((AbstractBean) bean);
        }
        return bean;
    }

    private void validate(AbstractBean abstractBean) {
        if (abstractBean.getName() == null) {
            abstractBean.setName("Default");
        }
        if (abstractBean.getValue() == null || abstractBean.getValue() < 0) {
            abstractBean.setValue(1);
        }
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
