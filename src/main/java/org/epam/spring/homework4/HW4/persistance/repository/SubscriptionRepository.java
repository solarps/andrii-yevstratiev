package org.epam.spring.homework4.HW4.persistance.repository;

import org.epam.spring.homework4.HW4.persistance.entity.Subscription;
import org.epam.spring.homework4.HW4.persistance.entity.composite.UserActivityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UserActivityKey> {
  @Modifying
  @Transactional
  void addSubscription(@Param("user_id") Long userId, @Param("activity_id") Long activityId);

  @Modifying
  @Transactional
  void deleteUserSubscription(@Param("user_id") Long userId, @Param("activity_id") Long activityID);

  @Modifying
  @Transactional
  void setSpentTimeToSubscription(
      @Param("user_id") Long userId,
      @Param("activity_id") Long activityId,
      @Param("spentTime") String spentTime);

  List<Subscription> getAllSubsForUser(@Param("user_id") Long id);
}
