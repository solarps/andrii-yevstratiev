package org.epam.spring.homework4.HW4.controller.dto;

import lombok.Data;
import org.epam.spring.homework4.HW4.controller.dto.validation.SpentTimeConstraint;

@Data
public class SpentTimeDTO {
  @SpentTimeConstraint String spentTime;
}
