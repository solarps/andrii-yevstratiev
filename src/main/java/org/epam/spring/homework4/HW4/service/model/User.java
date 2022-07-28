package org.epam.spring.homework4.HW4.service.model;

import lombok.Data;
import org.epam.spring.homework4.HW4.service.exception.EntityNotFoundException;

import java.util.List;

@Data
public class User {
  private String name;
  private String login;
  private List<Activity> activities;

  public Activity getActivityById(String id) {
    return activities.stream()
        .filter(activity -> activity.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new EntityNotFoundException("Activity not found"));
  }

  public boolean hasActivity(String id) {
    return activities.stream().anyMatch(activity -> activity.getId().equals(id));
  }
}
