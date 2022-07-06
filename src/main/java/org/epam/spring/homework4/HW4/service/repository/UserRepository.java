package org.epam.spring.homework4.HW4.service.repository;

import org.epam.spring.homework4.HW4.service.model.User;

import java.util.List;

public interface UserRepository {

  User getUserByLogin(String login);

  List<User> listUsers();

  User createUser(User user);

  User updateUser(String oldLogin, User user);

  void deleteUser(String login);
}
