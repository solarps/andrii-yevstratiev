package org.epam.spring.homework4.HW4.controller.dto.validation;

import org.epam.spring.homework4.HW4.controller.dto.SpentTimeDTO;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpentTimeValidatorTest {

  @Test
  void spentTimeInvalidCaseTest() throws NoSuchFieldException {
    SpentTimeConstraint spentTimeConstraint =
        SpentTimeDTO.class.getDeclaredField("spentTime").getAnnotation(SpentTimeConstraint.class);
    if (spentTimeConstraint != null) {
      Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

      SpentTimeDTO spentTimeDTO = new SpentTimeDTO("100:12312:1231");
      Set<ConstraintViolation<SpentTimeDTO>> violations = validator.validate(spentTimeDTO);
      List<String> messages =
          violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
      assertThat(messages, hasItem(spentTimeConstraint.message()));
    }
  }

  @Test
  void spentTimeValidCaseTest() throws NoSuchFieldException {
    SpentTimeConstraint spentTimeConstraint =
        SpentTimeDTO.class.getDeclaredField("spentTime").getAnnotation(SpentTimeConstraint.class);
    if (spentTimeConstraint != null) {
      Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

      SpentTimeDTO spentTimeDTO = new SpentTimeDTO("10:11:12");
      Set<ConstraintViolation<SpentTimeDTO>> violations = validator.validate(spentTimeDTO);
      assertTrue(violations.isEmpty());
    }
  }
}
