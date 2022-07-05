package org.epam.spring.homework3.HW3.service;

import org.epam.spring.homework3.HW3.controller.dto.ActivityDTO;

import java.util.List;

public interface ActivityService {

  ActivityDTO createActivity(ActivityDTO activityDTO);

  ActivityDTO getActivity(String id);

  List<ActivityDTO> listActivity();

  ActivityDTO updateActivity(String id, ActivityDTO activityDTO);

  void deleteActivity(String id);
}
