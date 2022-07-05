package org.epam.spring.homework3.HW3.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epam.spring.homework3.HW3.controller.dto.ActivityDTO;
import org.epam.spring.homework3.HW3.controller.dto.UserDTO;
import org.epam.spring.homework3.HW3.controller.dto.validategroup.OnCreate;
import org.epam.spring.homework3.HW3.controller.dto.validategroup.OnUpdate;
import org.epam.spring.homework3.HW3.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/user")
  public List<UserDTO> getAllUsers() {
    log.info("Controller: get all users");
    return userService.listUsers();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/user/{login}")
  public UserDTO getUserByLogin(@PathVariable String login) {
    log.info("Controller: get user by login: {}", login);
    return userService.getUserByLogin(login);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/user")
  public UserDTO createUser(@Validated(OnCreate.class) @RequestBody UserDTO userDTO) {
    log.info("Controller: create user: {}", userDTO);
    return userService.createUser(userDTO);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/user/{oldLogin}")
  public UserDTO updateUser(
      @PathVariable String oldLogin, @Validated(OnUpdate.class) @RequestBody UserDTO userDTO) {
    log.info("Controller: update user by login: {}, new user:{}", oldLogin, userDTO);
    return userService.updateUser(oldLogin, userDTO);
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/user/{login}")
  public ResponseEntity<Void> deleteUser(@PathVariable String login) {
    log.info("Controller: delete user by login: {}", login);
    userService.deleteUser(login);
    return ResponseEntity.noContent().build();
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("user/{login}/activity/{id}")
  public UserDTO addActivityForUser(@PathVariable String login, @PathVariable String id) {
    log.info("Controller: add activity to user: {} by id: {}", login, id);
    return userService.addActivity(login, id);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("user/{login}/activity")
  public List<ActivityDTO> userActivities(@PathVariable String login) {
    log.info("Controller: get all user activities by user login: {}", login);
    return userService.getUserActivities(login);
  }
}
