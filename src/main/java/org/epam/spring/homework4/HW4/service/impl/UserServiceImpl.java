package org.epam.spring.homework4.HW4.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epam.spring.homework4.HW4.controller.dto.UserDTO;
import org.epam.spring.homework4.HW4.persistance.entity.User;
import org.epam.spring.homework4.HW4.persistance.repository.UserRepository;
import org.epam.spring.homework4.HW4.service.UserService;
import org.epam.spring.homework4.HW4.service.exception.EntityNotFoundException;
import org.epam.spring.homework4.HW4.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Override
  public UserDTO getUserByLogin(String login) {
    log.info("Service: get user by login: {}", login);
    User user = userRepository.findByLogin(login).orElseThrow(EntityNotFoundException::new);
    return UserMapper.instance.mapToUserDTO(user);
  }

  @Override
  public List<UserDTO> listUsers() {
    log.info("Service: get all users");
    List<User> users = userRepository.findAll();
    return UserMapper.instance.mapToUserDTOS(users);
  }

  @Override
  public UserDTO createUser(UserDTO user) {
    log.info("Service: create user: {}", user);
    User createdUser = userRepository.save(UserMapper.instance.mapToUser(user));
    return UserMapper.instance.mapToUserDTO(createdUser);
  }

  @Override
  public UserDTO updateUser(String login, UserDTO user) {
    log.info("Service: update user by oldLogin: {}", login);
    User oldUser = userRepository.findByLogin(login).orElseThrow(EntityNotFoundException::new);
    userRepository.save(UserMapper.instance.mapToUser(user));
    return UserMapper.instance.mapToUserDTO(oldUser);
  }

  @Override
  public void deleteUser(Long id) {
    log.info("Service: delete user by login");
    userRepository.deleteById(id);
  }
}
