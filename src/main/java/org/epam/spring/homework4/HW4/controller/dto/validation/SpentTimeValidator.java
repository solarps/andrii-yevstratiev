package org.epam.spring.homework4.HW4.controller.dto.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.regex.Pattern;

public class SpentTimeValidator implements ConstraintValidator<SpentTimeConstraint, String> {
  @Override
  public void initialize(SpentTimeConstraint spentTimeConstraint) {}

  @Override
  public boolean isValid(String timeField, ConstraintValidatorContext cxt) {
    String[] time;
    if (timeField != null) {
      time = timeField.split(":");
    } else return false;
    return time.length == 3
        && Arrays.stream(time).noneMatch(x -> Pattern.matches("(.*)\\D(.*)", x))
        && time[0].length() >= 2
        && time[1].length() == 2
        && Integer.parseInt(time[1]) < 60
        && time[2].length() == 2
        && Integer.parseInt(time[2]) < 60;
  }
}
