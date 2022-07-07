package org.epam.spring.homework4.HW4.service.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.epam.spring.homework4.HW4.service.repository.ActivityRepository;
import org.epam.spring.homework4.HW4.service.exception.EntityExistsException;
import org.epam.spring.homework4.HW4.service.exception.EntityNotFoundException;
import org.epam.spring.homework4.HW4.service.model.Activity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class ActivityRepositoryImpl implements ActivityRepository {
  private final Map<String, Activity> activities = new HashMap<>();

  @Override
  public List<Activity> listActivity() {
    log.info("Repository: get all activities");
    return new ArrayList<>(activities.values());
  }

  @Override
  public Activity getActivityById(String id) {
    log.info("Repository: get activity by id");
    if (activities.containsKey(id)) return activities.get(id);
    else throw new EntityNotFoundException("Activity not found");
  }

  @Override
  public Activity createActivity(Activity activity) {
    log.info("Repository: create activity:{} ", activity);
    if (activities.values().stream()
        .noneMatch(
            activity1 ->
                activity1.getName().equals(activity.getName())
                    && activity1.getCategory().equals(activity.getCategory()))) {
      activity.setId(UUID.randomUUID().toString());
      activities.put(activity.getId(), activity);
      return activity;
    } else throw new EntityExistsException("Activity already exists");
  }

  @Override
  public Activity updateActivity(String id, Activity activity) {
    log.info("Repository: update activity by id: {}", id);
    if (activities.containsKey(id)) return activities.put(id, activity);
    else throw new EntityNotFoundException("Activity not found");
  }

  @Override
  public void deleteActivity(String id) {
    log.info("Repository: delete activity by id: {}", id);
    if (activities.containsKey(id)) activities.remove(id);
    else throw new EntityNotFoundException("Activity not found");
  }

  @Override
  public Boolean isActivityExists(String id) {
    log.info("Repository: check is activity exist by id: {}", id);
    return activities.containsKey(id);
  }
}
