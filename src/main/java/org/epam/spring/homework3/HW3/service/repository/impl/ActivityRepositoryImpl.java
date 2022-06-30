package org.epam.spring.homework3.HW3.service.repository.impl;

import org.epam.spring.homework3.HW3.service.model.Activity;
import org.epam.spring.homework3.HW3.service.repository.ActivityRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ActivityRepositoryImpl implements ActivityRepository {

    private final Map<String, Activity> activities = new HashMap<>();

    @Override
    public List<Activity> listActivity() {
        return new ArrayList<>(activities.values());
    }

    @Override
    public Activity getActivityById(String id) {
        return activities.get(id);
    }

    @Override
    public Activity createActivity(Activity activity) {
        activity.setId(UUID.randomUUID().toString());
        activities.put(activity.getId(), activity);
        return activity;
    }

    @Override
    public Activity updateActivity(String id, Activity activity) {
        return activities.put(id, activity);
    }

    @Override
    public void deleteActivity(String id) {
        activities.remove(id);
    }

    public Boolean isActivityExists(String id){
        return activities.containsKey(id);
    }
}
