package org.epam.spring.homework4.HW4.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epam.spring.homework4.HW4.controller.dto.ActivityDTO;
import org.epam.spring.homework4.HW4.persistance.entity.Activity;
import org.epam.spring.homework4.HW4.persistance.repository.ActivityRepository;
import org.epam.spring.homework4.HW4.service.ActivityService;
import org.epam.spring.homework4.HW4.service.exception.EntityNotFoundException;
import org.epam.spring.homework4.HW4.service.mapper.ActivityMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {
  private final ActivityRepository activityRepository;

  @Override
  public ActivityDTO createActivity(ActivityDTO activityDTO) {
    log.info("Service: create activity:{}", activityDTO);
    Activity createdActivity =
        activityRepository.save(ActivityMapper.instance.mapToActivity(activityDTO));
    return ActivityMapper.instance.mapToActivityDTO(createdActivity);
  }

  @Override
  public ActivityDTO getActivity(Long id) {
    log.info("Service: get activity by id: {}", id);
    Activity activity = activityRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    return ActivityMapper.instance.mapToActivityDTO(activity);
  }

  @Override
  public List<ActivityDTO> listActivity() {
    log.info("Service: get all activities");
    List<Activity> activities = activityRepository.findAll();
    return ActivityMapper.instance.mapToActivityDTOS(activities);
  }

  @Override
  public ActivityDTO updateActivity(Long id, ActivityDTO activityDTO) {
    log.info("Service: update activity by id:{}", id);
    Activity oldActivity =
        activityRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    activityRepository.save(ActivityMapper.instance.mapToActivity(activityDTO));
    return ActivityMapper.instance.mapToActivityDTO(oldActivity);
  }

  @Override
  public void deleteActivity(Long id) {
    log.info("Service: delete activity by id: {}", id);
    activityRepository.deleteById(id);
  }
}
