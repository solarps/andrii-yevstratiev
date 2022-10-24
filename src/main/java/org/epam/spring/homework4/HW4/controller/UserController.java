package org.epam.spring.homework4.HW4.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epam.spring.homework4.HW4.controller.api.UserApi;
import org.epam.spring.homework4.HW4.controller.dto.UserDTO;
import org.epam.spring.homework4.HW4.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
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

  public UserDTO updateUser(String login, UserDTO userDTO) {
    log.info("Controller: update user by login: {}, new user:{}", login, userDTO);
    return userService.updateUser(login, userDTO);
  }

  public ResponseEntity<Void> deleteUser(Long id) {
    log.info("Controller: delete user by id: {}", id);
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
}
