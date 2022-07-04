package org.epam.spring.homework3.HW3.service.repository;

import org.epam.spring.homework3.HW3.service.model.Activity;


import java.util.List;

public interface ActivityRepository {

    List<Activity> listActivity();

    Activity getActivityById(String id);

    Activity createActivity(Activity activity);

    Activity updateActivity(String id, Activity activity);

    void deleteActivity(String id);

    Boolean isActivityExists(String id);

}
