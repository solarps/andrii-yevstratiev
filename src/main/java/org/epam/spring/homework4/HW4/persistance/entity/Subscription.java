package org.epam.spring.homework4.HW4.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.epam.spring.homework4.HW4.persistance.entity.composite.UserActivityKey;

import javax.persistence.*;

@Entity(name = "SUBSCRIPTION")
@NamedQuery(
    name = "Subscription.getAllSubsForUser",
    query = "SELECT sub FROM SUBSCRIPTION sub WHERE sub.user.id = :user_id")
@NamedNativeQuery(
    name = "Subscription.addSubscription",
    query =
        "INSERT INTO SUBSCRIPTION (user_id, ACTIVITY_ID, SPENT_TIME) VALUES (:user_id, :activity_id, '00:00:00')")
@NamedNativeQuery(
    name = "Subscription.deleteUserSubscription",
    query = "DELETE FROM SUBSCRIPTION WHERE user_id =:user_id AND activity_id=:activity_id ")
@NamedNativeQuery(
    name = "Subscription.setSpentTimeToSubscription",
    query =
        "UPDATE SUBSCRIPTION SET spent_time=CAST(:spentTime as interval HOUR TO SECOND) WHERE user_id=:user_id and activity_id =:activity_id")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subscription {
  @EmbeddedId UserActivityKey pk;

  @ManyToOne
  @MapsId("userId")
  @JoinColumn(name = "user_id")
  User user;

  @ManyToOne
  @MapsId("activityId")
  @JoinColumn(name = "activity_id")
  Activity activity;

  @Column(name = "spent_time")
  String spentTime;
}
