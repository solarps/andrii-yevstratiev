package org.epam.spring.homework3.HW3.repository;

import org.epam.spring.homework3.HW3.service.model.User;

import java.util.List;

public interface UserRepository {


    User getUserByLogin(String login);

    List<User> listUsers();

    User createUser(User user);

    User updateUser(String login, User user);

    void deleteUser(String login);
}
