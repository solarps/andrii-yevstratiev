package org.epam.spring.homework4.HW4.service;

import org.epam.spring.homework4.HW4.controller.dto.ActivityDTO;

import java.util.List;

public interface ActivityService {
  ActivityDTO createActivity(ActivityDTO activityDTO);

  ActivityDTO getActivity(Long id);

  List<ActivityDTO> listActivity();

  ActivityDTO updateActivity(Long id, ActivityDTO activityDTO);

  void deleteActivity(Long id);
}
