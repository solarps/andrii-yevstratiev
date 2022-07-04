package org.epam.spring.homework3.HW3.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epam.spring.homework3.HW3.controller.dto.ActivityDTO;
import org.epam.spring.homework3.HW3.service.repository.UserRepository;
import org.epam.spring.homework3.HW3.service.ActivityService;
import org.epam.spring.homework3.HW3.service.mapper.ActivityMapper;
import org.epam.spring.homework3.HW3.service.model.Activity;
import org.epam.spring.homework3.HW3.service.repository.ActivityRepository;
import org.epam.spring.homework3.HW3.service.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    private final UserRepository userRepository;

    @Override
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        log.info("Service: create activity:{}", activityDTO);
        Activity createdActivity = activityRepository.createActivity(ActivityMapper.instance.mapToActivity(activityDTO));
        return ActivityMapper.instance.mapToActivityDTO(createdActivity);
    }

    @Override
    public ActivityDTO getActivity(String id) {
        log.info("Service: get activity by id: {}", id);
        return ActivityMapper.instance.mapToActivityDTO(activityRepository.getActivityById(id));
    }

    @Override
    public List<ActivityDTO> listActivity() {
        log.info("Service: get all activities");
        List<Activity> activities = activityRepository.listActivity();
        return ActivityMapper.instance.mapToActivityDTOS(activities);
    }

    @Override
    public ActivityDTO updateActivity(String id, ActivityDTO activityDTO) {
        log.info("Service: update activity by id:{}", id);
        Activity activity = activityRepository.updateActivity(id, ActivityMapper.instance.mapToActivity(activityDTO));
        userRepository.listUsers().forEach(user -> {
            if (userHasActivity(user, id)) {
                user.getActivities().set(user.getActivities().indexOf(activity), ActivityMapper.instance.mapToActivity(activityDTO));
            }
        });
        return ActivityMapper.instance.mapToActivityDTO(activity);
    }

    @Override
    public void deleteActivity(String id) {
        log.info("Service: delete activity by id: {}", id);
        activityRepository.deleteActivity(id);
        userRepository.listUsers().forEach(user -> {
            if (userHasActivity(user, id)) {
                user.getActivities().removeIf(activity -> activity.getId().equals(id));
                if (user.getActivities().size() == 0) user.setActivities(null);
            }
        });
    }

    private boolean userHasActivity(User user, String id) {
        return user.getActivities().stream().anyMatch(activity -> activity.getId().equals(id));
    }

}