package org.epam.spring.homework4.HW4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epam.spring.homework4.HW4.controller.api.SubscriptionAPI;
import org.epam.spring.homework4.HW4.controller.dto.SpentTimeDTO;
import org.epam.spring.homework4.HW4.controller.dto.SubscriptionDTO;
import org.epam.spring.homework4.HW4.controller.dto.UserDTO;
import org.epam.spring.homework4.HW4.service.SubscriptionService;
import org.epam.spring.homework4.HW4.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SubscriptionController implements SubscriptionAPI {
  private final SubscriptionService subscriptionService;
  private final UserService userService;

  @Override
  public List<SubscriptionDTO> getAllSubscription() {
    log.info("Controller: get all subs");
    return subscriptionService.getAllSubscription();
  }

  @Override
  public List<SubscriptionDTO> getAllSubscriptionForUser(String login) {
    log.info("Controller: get all subs for user:{}", login);
    UserDTO userDTO = userService.getUserByLogin(login);
    return subscriptionService.getAllUserSubscription(userDTO.getId());
  }

  @Override
  public SubscriptionDTO addSubscription(String login, Long activityID) {
    log.info("Controller: add new subscription");
    UserDTO userDTO = userService.getUserByLogin(login);
    return subscriptionService.addSubscription(userDTO.getId(), activityID);
  }

  @Override
  public SubscriptionDTO setSpentTime(String login, Long activityID, SpentTimeDTO spentTimeDTO) {
    log.info("Controller: set spent time");
    UserDTO userDTO = userService.getUserByLogin(login);
    return subscriptionService.setSpentTimeForSubscription(
        userDTO.getId(), activityID, spentTimeDTO.getSpentTime());
  }

  @Override
  public ResponseEntity<Void> deleteSubscription(String login, Long activityID) {
    log.info("Controller: delete subscription");
    UserDTO userDTO = userService.getUserByLogin(login);
    subscriptionService.deleteSubscription(userDTO.getId(), activityID);
    return ResponseEntity.noContent().build();
  }
}
