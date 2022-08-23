package org.epam.spring.homework4.HW4.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SubscriptionDTO {
  private String userLogin;
  private String activityName;
  private String spentTime;
}
