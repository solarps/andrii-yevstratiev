package org.epam.spring.homework4.HW4.service;

import org.epam.spring.homework4.HW4.controller.dto.UserDTO;

import java.util.List;

public interface UserService {
  UserDTO getUserByLogin(String login);

  List<UserDTO> listUsers();

  UserDTO createUser(UserDTO user);

  UserDTO updateUser(String oldLogin, UserDTO user);

  void deleteUser(Long id);
}
