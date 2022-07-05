package org.epam.spring.homework3.HW3.service.exception;

import org.epam.spring.homework3.HW3.service.model.enums.ErrorType;

public class EntityNotFoundException extends ServiceException {
  public static final String DEFAULT_MESSAGE = "Entity not found";

  public EntityNotFoundException() {
    super(DEFAULT_MESSAGE);
  }

  public EntityNotFoundException(String message) {
    super(message);
  }

  @Override
  public ErrorType getErrorType() {
    return ErrorType.VALIDATION_ERROR_TYPE;
  }
}
