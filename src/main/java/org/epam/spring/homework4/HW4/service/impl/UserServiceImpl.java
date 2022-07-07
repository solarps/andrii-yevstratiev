package org.epam.spring.homework4.HW4.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epam.spring.homework4.HW4.controller.dto.ActivityDTO;
import org.epam.spring.homework4.HW4.controller.dto.UserDTO;
import org.epam.spring.homework4.HW4.service.UserService;
import org.epam.spring.homework4.HW4.service.exception.EntityExistsException;
import org.epam.spring.homework4.HW4.service.exception.EntityNotFoundException;
import org.epam.spring.homework4.HW4.service.mapper.UserMapper;
import org.epam.spring.homework4.HW4.service.model.User;
import org.epam.spring.homework4.HW4.service.repository.ActivityRepository;
import org.epam.spring.homework4.HW4.service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final ActivityRepository activityRepository;

  @Override
  public UserDTO getUserByLogin(String login) {
    log.info("Service: get user by login: {}", login);
    User user = userRepository.getUserByLogin(login);
    return UserMapper.instance.mapToUserDTO(user);
  }

  @Override
  public List<UserDTO> listUsers() {
    log.info("Service: get all users");
    List<User> users = userRepository.listUsers();
    return UserMapper.instance.mapToUserDTOS(users);
  }

  @Override
  public UserDTO createUser(UserDTO user) {
    log.info("Service: create user: {}", user);
    User createdUser = userRepository.createUser(UserMapper.instance.mapToUser(user));
    return UserMapper.instance.mapToUserDTO(createdUser);
  }

  @Override
  public UserDTO updateUser(String oldLogin, UserDTO user) {
    log.info("Service: update user by oldLogin: {}", oldLogin);
    User oldUser = userRepository.updateUser(oldLogin, UserMapper.instance.mapToUser(user));
    return UserMapper.instance.mapToUserDTO(oldUser);
  }

  @Override
  public void deleteUser(String login) {
    log.info("Service: delete user by login");
    userRepository.deleteUser(login);
  }

  @Override
  public UserDTO addActivity(String login, String id) {
    log.info("Service: add activity {} for user: {}", id, login);
    User user = userRepository.getUserByLogin(login);
    if (activityRepository.isActivityExists(id)) {
      if (user.getActivities() == null) {
        user.setActivities(new ArrayList<>());
      }
      if (user.getActivities().stream().noneMatch(activity -> activity.getId().equals(id))) {
        user.getActivities().add(activityRepository.getActivityById(id).clone());
        return UserMapper.instance.mapToUserDTO(user);
      } else throw new EntityExistsException("User already follows this activity");
    } else throw new EntityNotFoundException("Activity not found");
  }

  @Override
  public List<ActivityDTO> getUserActivities(String login) {
    log.info("Service: get activities for user: {}", login);
    return getUserByLogin(login).getActivities();
  }
}
