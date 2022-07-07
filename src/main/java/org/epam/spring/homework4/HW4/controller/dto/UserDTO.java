package org.epam.spring.homework4.HW4.controller.dto;

import lombok.Data;
import org.epam.spring.homework4.HW4.controller.dto.validation.group.OnCreate;
import org.epam.spring.homework4.HW4.controller.dto.validation.group.OnUpdate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class UserDTO {
  @NotBlank(
      message = "name shouldn't be empty",
      groups = {OnCreate.class, OnUpdate.class})
  private String name;

  @NotBlank(
      message = "login shouldn't be empty",
      groups = {OnCreate.class, OnUpdate.class})
  @Pattern(
      regexp = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{4,19}$",
      message = "login should starts from letter and consist of 4 to 19 characters",
      groups = {OnCreate.class, OnUpdate.class})
  private String login;

  @Null(message = "activities should be null", groups = OnCreate.class)
  private List<ActivityDTO> activities;
}
