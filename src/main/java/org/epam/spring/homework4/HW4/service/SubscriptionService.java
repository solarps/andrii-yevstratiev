package org.epam.spring.homework4.HW4.service;

import org.epam.spring.homework4.HW4.controller.dto.SubscriptionDTO;

import java.util.List;

public interface SubscriptionService {
  SubscriptionDTO addSubscription(Long userId, Long activityId);

  void deleteSubscription(Long userId, Long activityId);

  List<SubscriptionDTO> getAllUserSubscription(Long userId);

  SubscriptionDTO setSpentTimeForSubscription(Long userId, Long activityId, String spentTime);

  List<SubscriptionDTO> getAllSubscription();
}
