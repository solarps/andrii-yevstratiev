package org.epam.spring.homework3.HW3.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
public class ActivityDTO {
  @JsonProperty(access = READ_ONLY)
  private String id;

  private String name;
  private String spentTime;
  private String category;
}
