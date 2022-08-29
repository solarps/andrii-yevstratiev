package org.epam.spring.homework4.HW4.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.epam.spring.homework4.HW4.controller.dto.validation.SpentTimeConstraint;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpentTimeDTO {
  @SpentTimeConstraint String spentTime;
}
