package org.epam.spring.homework3.HW3.service.repository.impl;

import org.epam.spring.homework3.HW3.service.model.User;
import org.epam.spring.homework3.HW3.service.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public User getUserByLogin(String login) {
        return users.get(login);
    }

    @Override
    public List<User> listUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User createUser(User user) {
        if (!users.containsKey(user.getLogin())) {
            users.put(user.getLogin(), user);
            return user;
        } else {

            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @Override
    public User updateUser(String login, User user) {
        if (!users.containsKey(login)) {
            return users.put(login, user);
        } else throw new RuntimeException("User already exists");
    }

    @Override
    public void deleteUser(String login) {
        users.remove(login);
    }
}
