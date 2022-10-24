package org.epam.spring.homework4.HW4.controller.validation;

import lombok.extern.slf4j.Slf4j;
import org.epam.spring.homework4.HW4.service.exception.EntityExistsException;
import org.epam.spring.homework4.HW4.service.exception.EntityNotFoundException;
import org.epam.spring.homework4.HW4.service.model.Error;
import org.epam.spring.homework4.HW4.service.model.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public List<Error> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception) {
    log.error("handleMethodArgumentNotValidException: {}", exception.getMessage(), exception);
    return exception.getBindingResult().getAllErrors().stream()
        .map(
            err ->
                new Error(
                    err.getDefaultMessage(), ErrorType.VALIDATION_ERROR_TYPE, LocalDateTime.now()))
        .collect(Collectors.toList());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Error handleEntityNotFoundException(EntityNotFoundException exception) {
    log.error("handleEntityNotFoundException: {}", exception.getMessage(), exception);
    return new Error(exception.getMessage(), exception.getErrorType(), LocalDateTime.now());
  }

  @ExceptionHandler(EntityExistsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Error handleEntityExistsException(EntityExistsException exception) {
    log.error("handleEntityExistsException: {}", exception.getMessage(), exception);
    return new Error(exception.getMessage(), exception.getErrorType(), LocalDateTime.now());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Error handleException(Exception e) {
    log.error("handleException: {}", e.getMessage());
    return new Error(e.getMessage(), ErrorType.FATAL_ERROR_TYPE, LocalDateTime.now());
  }
}
