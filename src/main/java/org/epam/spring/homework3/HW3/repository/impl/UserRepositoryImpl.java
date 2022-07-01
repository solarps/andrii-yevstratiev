package org.epam.spring.homework3.HW3.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.epam.spring.homework3.HW3.repository.UserRepository;
import org.epam.spring.homework3.HW3.service.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public User getUserByLogin(String login) {
        log.info("Repository: get user by login: {}", login);
        return users.get(login);
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

            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @Override
    public User updateUser(String oldLogin, User user) {
        log.info("Repository: update user by login:{}", oldLogin);
        if (users.containsKey(oldLogin)) {
            users.put(user.getLogin(),user);
            return users.remove(oldLogin);
        } else throw new RuntimeException("User doesn't exists");
    }

    @Override
    public void deleteUser(String login) {
        log.info("Repository: delete user by login:{}", login);
        users.remove(login);
    }
}
