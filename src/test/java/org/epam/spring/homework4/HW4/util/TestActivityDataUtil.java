package org.epam.spring.homework4.HW4.util;

import org.epam.spring.homework4.HW4.controller.dto.ActivityDTO;
import org.epam.spring.homework4.HW4.persistance.entity.Activity;

import java.util.List;

public class TestActivityDataUtil {

  public static final Long ID = 1L;
  public static final String NAME = "Football";
  public static final String CATEGORY = "Sport";

  public static Activity createActivity() {
    return Activity.builder().id(ID).name(NAME).category(CATEGORY).build();
  }

  public static ActivityDTO createActivityDTO() {
    return ActivityDTO.builder().id(ID).name(NAME).category(CATEGORY).build();
  }

  public static List<Activity> createActivityList() {
    return List.of(createActivity(), createActivity());
  }

  public static List<ActivityDTO> createActivityDTOList() {
    return List.of(createActivityDTO(), createActivityDTO());
  }
}
