package org.epam.spring.homework3.HW3.service.exception;

import org.epam.spring.homework3.HW3.service.model.enums.ErrorType;

public class EntityExistsException extends ServiceException {

  public static final String DEFAULT_MESSAGE = "Entity already exists";

  public EntityExistsException(String message) {
    super(message);
  }

  public EntityExistsException() {
    super(DEFAULT_MESSAGE);
  }

  @Override
  public ErrorType getErrorType() {
    return ErrorType.VALIDATION_ERROR_TYPE;
  }
}
