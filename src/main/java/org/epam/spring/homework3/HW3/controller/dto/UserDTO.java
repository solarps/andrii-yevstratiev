package org.epam.spring.homework3.HW3.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
  private String name;
  private String login;
  private List<ActivityDTO> activities;
}
