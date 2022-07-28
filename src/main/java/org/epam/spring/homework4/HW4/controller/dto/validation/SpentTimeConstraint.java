package org.epam.spring.homework4.HW4.controller.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SpentTimeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SpentTimeConstraint {
  String message() default "{spent-time.validation.error}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
