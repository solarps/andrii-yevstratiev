package org.epam.spring.homework4.HW4.service.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
  private String name;
  private String login;
  private List<Activity> activities;
}
