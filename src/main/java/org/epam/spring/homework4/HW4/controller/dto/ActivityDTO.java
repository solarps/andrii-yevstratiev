package org.epam.spring.homework4.HW4.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.epam.spring.homework4.HW4.controller.dto.validation.SpentTimeConstraint;
import org.epam.spring.homework4.HW4.controller.dto.validation.group.OnCreate;
import org.epam.spring.homework4.HW4.controller.dto.validation.group.OnSetTime;
import org.epam.spring.homework4.HW4.controller.dto.validation.group.OnUpdate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
public class ActivityDTO {
  @JsonProperty(access = READ_ONLY)
  private String id;

  @NotBlank(
      message = "{name.not.empty}",
      groups = {OnCreate.class, OnUpdate.class})
  @Null(message = "name.null", groups = OnSetTime.class)
  private String name;

  @Null(
      message = "{spent-time.null}",
      groups = {OnCreate.class, OnUpdate.class})
  @SpentTimeConstraint(groups = OnSetTime.class)
  private String spentTime;

  @NotBlank(
      message = "{name.not.empty}",
      groups = {OnCreate.class, OnUpdate.class})
  @Null(message = "{category.null}", groups = OnSetTime.class)
  private String category;
}
