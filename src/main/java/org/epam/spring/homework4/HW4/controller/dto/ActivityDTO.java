package org.epam.spring.homework4.HW4.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.epam.spring.homework4.HW4.controller.dto.validation.group.OnCreate;
import org.epam.spring.homework4.HW4.controller.dto.validation.group.OnUpdate;

import javax.validation.constraints.NotBlank;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@Builder
public class ActivityDTO {
  @JsonProperty(access = READ_ONLY)
  private Long id;

  @NotBlank(
      message = "{name.not.empty}",
      groups = {OnCreate.class, OnUpdate.class})
  private String name;

  @NotBlank(
      message = "{name.not.empty}",
      groups = {OnCreate.class, OnUpdate.class})
  private String category;
}
