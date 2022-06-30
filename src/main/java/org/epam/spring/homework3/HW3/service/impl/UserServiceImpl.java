package org.epam.spring.homework3.HW3.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epam.spring.homework3.HW3.controller.dto.ActivityDTO;
import org.epam.spring.homework3.HW3.controller.dto.UserDTO;
import org.epam.spring.homework3.HW3.service.UserService;
import org.epam.spring.homework3.HW3.service.mapper.UserMapper;
import org.epam.spring.homework3.HW3.service.model.User;
import org.epam.spring.homework3.HW3.service.repository.ActivityRepository;
import org.epam.spring.homework3.HW3.service.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public UserDTO updateUser(String login, UserDTO user) {
        log.info("Service: update user by login: {}", login);
        User oldUser = userRepository.updateUser(login, UserMapper.instance.mapToUser(user));
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
        if (activityRepository.isActivityExists(id) &&
                user.getActivities().stream().noneMatch(activity -> activity.getId().equals(id))) {
            user.getActivities().add(activityRepository.getActivityById(id));
            return UserMapper.instance.mapToUserDTO(user);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<ActivityDTO> getUserActivities(String login) {
        log.info("Service: get activities for user: {}", login);
        return getUserByLogin(login).getActivities();
    }
}