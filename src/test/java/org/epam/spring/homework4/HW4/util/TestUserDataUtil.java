package org.epam.spring.homework4.HW4.util;

import org.epam.spring.homework4.HW4.controller.dto.UserDTO;
import org.epam.spring.homework4.HW4.persistance.entity.User;

public class TestUserDataUtil {

  public static final Long ID = 1L;
  public static final String NAME = "Andrii";
  public static final String LOGIN = "Admin_123";

  public static User createUser() {
    return User.builder().id(ID).name(NAME).login(LOGIN).build();
  }

  public static UserDTO createUserDTO() {
    return UserDTO.builder().id(ID).name(NAME).login(LOGIN).build();
  }
}
