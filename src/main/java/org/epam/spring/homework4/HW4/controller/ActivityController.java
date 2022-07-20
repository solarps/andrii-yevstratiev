package org.epam.spring.homework4.HW4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epam.spring.homework4.HW4.controller.api.ActivityAPI;
import org.epam.spring.homework4.HW4.controller.dto.ActivityDTO;
import org.epam.spring.homework4.HW4.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ActivityController implements ActivityAPI {
  private final ActivityService activityService;

  public List<ActivityDTO> getAllActivity() {
    log.info("Controller: get all activities");
    return activityService.listActivity();
  }

  public ActivityDTO createActivity(ActivityDTO activityDTO) {
    log.info("Controller: create activity");
    return activityService.createActivity(activityDTO);
  }

  public ActivityDTO getActivityById(Long id) {
    log.info("Controller: get activity by id:{}", id);
    return activityService.getActivity(id);
  }

  public ActivityDTO updateActivity(Long id, ActivityDTO activityDTO) {
    log.info("Controller: update activity by id: {}", id);
    activityDTO.setId(id);
    return activityService.updateActivity(id, activityDTO);
  }

  public ResponseEntity<Void> deleteActivity(Long id) {
    log.info("Controller: delete activity by id: {}", id);
    activityService.deleteActivity(id);
    return ResponseEntity.noContent().build();
  }
}
