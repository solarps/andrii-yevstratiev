package org.epam.spring.homework4.HW4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epam.spring.homework4.HW4.controller.api.UserApi;
import org.epam.spring.homework4.HW4.controller.dto.ActivityDTO;
import org.epam.spring.homework4.HW4.controller.dto.UserDTO;
import org.epam.spring.homework4.HW4.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
  private final UserService userService;

  public List<UserDTO> getAllUsers() {
    log.info("Controller: get all users");
    return userService.listUsers();
  }

  public UserDTO getUserByLogin(String login) {
    log.info("Controller: get user by login: {}", login);
    return userService.getUserByLogin(login);
  }

  public UserDTO createUser(UserDTO userDTO) {
    log.info("Controller: create user: {}", userDTO);
    return userService.createUser(userDTO);
  }

  public UserDTO updateUser(String oldLogin, UserDTO userDTO) {
    log.info("Controller: update user by login: {}, new user:{}", oldLogin, userDTO);
    return userService.updateUser(oldLogin, userDTO);
  }

  public ResponseEntity<Void> deleteUser(String login) {
    log.info("Controller: delete user by login: {}", login);
    userService.deleteUser(login);
    return ResponseEntity.noContent().build();
  }

  public UserDTO addActivityForUser(String login, String id) {
    log.info("Controller: add activity to user: {} by id: {}", login, id);
    return userService.addActivity(login, id);
  }

  @Override
  public UserDTO setActivityTime(String login, String id, ActivityDTO activityDTO) {
    log.info("Controller: user {} set spent time to activity {}", login, id);
    return userService.setSpentTime(login, id, activityDTO);
  }

  public List<ActivityDTO> userActivities(String login) {
    log.info("Controller: get all user activities by user login: {}", login);
    return userService.getUserActivities(login);
  }
}
