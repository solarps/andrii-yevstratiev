package org.epam.spring.homework4.HW4.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epam.spring.homework4.HW4.controller.dto.SubscriptionDTO;
import org.epam.spring.homework4.HW4.persistance.entity.Subscription;
import org.epam.spring.homework4.HW4.persistance.entity.composite.UserActivityKey;
import org.epam.spring.homework4.HW4.persistance.repository.SubscriptionRepository;
import org.epam.spring.homework4.HW4.service.SubscriptionService;
import org.epam.spring.homework4.HW4.service.mapper.SubscriptionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
  private final SubscriptionRepository subscriptionRepository;

  @Override
  public SubscriptionDTO addSubscription(Long userId, Long activityId) {
    log.info("Service: add new subscription for user:{} and activity:{}", userId, activityId);
    subscriptionRepository.addSubscription(userId, activityId);
    return SubscriptionMapper.instance.mapToSubscriptionDTO(
        subscriptionRepository.getById(new UserActivityKey(userId, activityId)));
  }

  @Override
  public void deleteSubscription(Long userId, Long activityId) {
    log.info("Service: delete subscription user:{} activity:{}", userId, activityId);
    subscriptionRepository.deleteUserSubscription(userId, activityId);
  }

  @Override
  public List<SubscriptionDTO> getAllUserSubscription(Long userId) {
    log.info("Service: get all subscription for user:{}", userId);
    return SubscriptionMapper.instance.mapToSubscriptionDTOS(
        subscriptionRepository.getAllSubsForUser(userId));
  }

  @Override
  public SubscriptionDTO setSpentTimeForSubscription(
      Long userId, Long activityId, String spentTime) {
    log.info("Service: set spent time for subscription");
    subscriptionRepository.setSpentTimeToSubscription(userId, activityId, spentTime);
    return SubscriptionMapper.instance.mapToSubscriptionDTO(
        subscriptionRepository.getById(new UserActivityKey(userId, activityId)));
  }

  @Override
  public List<SubscriptionDTO> getAllSubscription() {
    log.info("Service: get all active subscription");
    List<Subscription> subs = subscriptionRepository.findAll();
    return SubscriptionMapper.instance.mapToSubscriptionDTOS(subs);
  }
}
