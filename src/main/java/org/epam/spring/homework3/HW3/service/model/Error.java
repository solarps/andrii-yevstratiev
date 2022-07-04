package org.epam.spring.homework3.HW3.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.epam.spring.homework3.HW3.service.model.enums.ErrorType;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class Error {
    private String message;
    private ErrorType errorType;
    private LocalDateTime errorTime;
}
