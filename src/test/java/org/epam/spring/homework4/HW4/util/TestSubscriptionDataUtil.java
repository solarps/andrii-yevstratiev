package org.epam.spring.homework4.HW4.util;

import org.epam.spring.homework4.HW4.controller.dto.SubscriptionDTO;
import org.epam.spring.homework4.HW4.persistance.entity.Activity;
import org.epam.spring.homework4.HW4.persistance.entity.Subscription;
import org.epam.spring.homework4.HW4.persistance.entity.User;
import org.epam.spring.homework4.HW4.persistance.entity.composite.UserActivityKey;

import java.util.List;

public class TestSubscriptionDataUtil {

  public static final User user = TestUserDataUtil.createUser();
  public static final Activity activity = TestActivityDataUtil.createActivity();
  public static final String spentTime = "00:00:00";

  public static Subscription createSub() {
    return Subscription.builder()
        .pk(new UserActivityKey(user.getId(), activity.getId()))
        .activity(activity)
        .user(user)
        .spentTime(spentTime)
        .build();
  }

  public static SubscriptionDTO createSubDTO() {
    return SubscriptionDTO.builder()
        .userLogin(user.getLogin())
        .activityName(activity.getName())
        .spentTime(spentTime)
        .build();
  }

  public static List<Subscription> createSubList() {
    return List.of(createSub(), createSub());
  }

  public static List<SubscriptionDTO> createSubDTOList() {
    return List.of(createSubDTO(), createSubDTO());
  }
}
