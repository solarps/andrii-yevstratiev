package org.epam.spring.homework3.HW3.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.epam.spring.homework3.HW3.repository.ActivityRepository;
import org.epam.spring.homework3.HW3.service.model.Activity;
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
    return activities.get(id);
  }

  @Override
  public Activity createActivity(Activity activity) {
    log.info("Repository: create activity:{} ", activity);
    activity.setId(UUID.randomUUID().toString());
    activities.put(activity.getId(), activity);
    return activity;
  }

  @Override
  public Activity updateActivity(String id, Activity activity) {
    log.info("Repository: update activity by id: {}", id);
    return activities.put(id, activity);
  }

  @Override
  public void deleteActivity(String id) {
    log.info("Repository: delete activity by id: {}", id);
    activities.remove(id);
  }

  @Override
  public Boolean isActivityExists(String id) {
    log.info("Repository: check is activity exist by id: {}", id);
    return activities.containsKey(id);
  }
}
