package org.epam.spring.homework3.HW3.service;

import org.epam.spring.homework3.HW3.controller.dto.ActivityDTO;
import org.epam.spring.homework3.HW3.controller.dto.UserDTO;

import java.util.List;

public interface UserService {
  UserDTO getUserByLogin(String login);

  List<UserDTO> listUsers();

  UserDTO createUser(UserDTO user);

  UserDTO updateUser(String oldLogin, UserDTO user);

  void deleteUser(String login);

  UserDTO addActivity(String login, String id);

  List<ActivityDTO> getUserActivities(String login);
}
