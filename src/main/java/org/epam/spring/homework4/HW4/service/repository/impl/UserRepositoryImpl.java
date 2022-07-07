package org.epam.spring.homework4.HW4.service.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.epam.spring.homework4.HW4.service.exception.EntityExistsException;
import org.epam.spring.homework4.HW4.service.exception.EntityNotFoundException;
import org.epam.spring.homework4.HW4.service.model.User;
import org.epam.spring.homework4.HW4.service.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {
  private final Map<String, User> users = new HashMap<>();

  @Override
  public User getUserByLogin(String login) {
    log.info("Repository: get user by login: {}", login);
    if (users.containsKey(login)) return users.get(login);
    else throw new EntityNotFoundException("User not found");
  }

  @Override
  public List<User> listUsers() {
    log.info("Repository: get all users");
    return new ArrayList<>(users.values());
  }

  @Override
  public User createUser(User user) {
    log.info("Repository: create user:{}", user);
    if (!users.containsKey(user.getLogin())) {
      users.put(user.getLogin(), user);
      return user;
    } else {
      throw new EntityExistsException("User already exists");
    }
  }

  @Override
  public User updateUser(String oldLogin, User user) {
    log.info("Repository: update user by login:{}", oldLogin);
    if (users.containsKey(oldLogin)) {
      users.put(user.getLogin(), user);
      return users.remove(oldLogin);
    } else throw new EntityNotFoundException("User not found");
  }

  @Override
  public void deleteUser(String login) {
    log.info("Repository: delete user by login:{}", login);
    if (!users.containsKey(login)) throw new EntityNotFoundException("User not found");
    users.remove(login);
  }
}
