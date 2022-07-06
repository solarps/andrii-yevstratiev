package org.epam.spring.homework4.HW4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epam.spring.homework4.HW4.controller.dto.ActivityDTO;
import org.epam.spring.homework4.HW4.controller.dto.validategroup.OnCreate;
import org.epam.spring.homework4.HW4.controller.dto.validategroup.OnUpdate;
import org.epam.spring.homework4.HW4.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ActivityController {
  private final ActivityService activityService;

  @GetMapping("/activity")
  public List<ActivityDTO> getAllActivity() {
    log.info("Controller: get all activities");
    return activityService.listActivity();
  }

  @PostMapping("/activity")
  public ActivityDTO createActivity(
      @Validated(OnCreate.class) @RequestBody ActivityDTO activityDTO) {
    log.info("Controller: create activity");
    return activityService.createActivity(activityDTO);
  }

  @GetMapping("/activity/{id}")
  public ActivityDTO getActivityById(@PathVariable String id) {
    log.info("Controller: get activity by id:{}", id);
    return activityService.getActivity(id);
  }

  @PutMapping("/activity/{id}")
  public ActivityDTO updateActivity(
      @Validated(OnUpdate.class) @PathVariable String id, @RequestBody ActivityDTO activityDTO) {
    log.info("Controller: update activity by id: {}", id);
    activityDTO.setId(id);
    return activityService.updateActivity(id, activityDTO);
  }

  @DeleteMapping("/activity/{id}")
  public ResponseEntity<Void> deleteActivity(@PathVariable String id) {
    log.info("Controller: delete activity by id: {}", id);
    activityService.deleteActivity(id);
    return ResponseEntity.noContent().build();
  }
}
