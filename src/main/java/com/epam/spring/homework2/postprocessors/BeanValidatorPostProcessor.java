package com.epam.spring.homework2.postprocessors;

import com.epam.spring.homework2.beans.AbstractBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanValidatorPostProcessor implements BeanPostProcessor {
  private static final int DEFAULT_VALUE = Integer.MAX_VALUE;
  private static final String DEFAULT_NAME = "DEFAULT";

  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (bean instanceof AbstractBean) {
      validate((AbstractBean) bean);
    }
    return bean;
  }

  private void validate(AbstractBean abstractBean) {
    if (abstractBean.getName() == null) {
      abstractBean.setName(DEFAULT_NAME);
    }
    if (abstractBean.getValue() == null || abstractBean.getValue() < 0) {
      abstractBean.setValue(DEFAULT_VALUE);
    }
  }
}
