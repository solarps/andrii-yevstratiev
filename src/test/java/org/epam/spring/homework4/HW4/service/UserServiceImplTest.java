package org.epam.spring.homework4.HW4.service;

import org.epam.spring.homework4.HW4.controller.dto.UserDTO;
import org.epam.spring.homework4.HW4.persistance.entity.User;
import org.epam.spring.homework4.HW4.persistance.repository.UserRepository;
import org.epam.spring.homework4.HW4.service.exception.EntityNotFoundException;
import org.epam.spring.homework4.HW4.service.impl.UserServiceImpl;
import org.epam.spring.homework4.HW4.util.TestUserDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @InjectMocks private UserServiceImpl userService;

  @Mock private UserRepository userRepository;

  @Test
  void getUserByLoginPositiveCase() {
    User user = TestUserDataUtil.createUser();
    UserDTO userDTO = TestUserDataUtil.createUserDTO();
    when(userRepository.findByLogin(user.getLogin())).thenReturn(Optional.of(user));

    assertEquals(userDTO, userService.getUserByLogin(user.getLogin()));
    verify(userRepository, times(1)).findByLogin(user.getLogin());
  }

  @Test
  void getUserByLoginNotFoundCase() {
    when(userRepository.findByLogin(any())).thenThrow(EntityNotFoundException.class);

    assertThrows(EntityNotFoundException.class, () -> userService.getUserByLogin(any()));
    verify(userRepository, times(1)).findByLogin(any());
  }

  @Test
  void listUsersTest() {
    List<User> userList = TestUserDataUtil.createUserList();
    when(userRepository.findAll()).thenReturn(userList);

    userService.listUsers();

    assertThat(userList, hasItem(TestUserDataUtil.createUser()));
    verify(userRepository, times(1)).findAll();
  }

  @Test
  void createUserTest() {
    User user = TestUserDataUtil.createUser();
    UserDTO userDTO = TestUserDataUtil.createUserDTO();
    when(userRepository.save(user)).thenReturn(user);

    assertEquals(userDTO, userService.createUser(userDTO));
    verify(userRepository, times(1)).save(user);
  }

  @Test
  void updateUserPositiveCase() {
    User user = TestUserDataUtil.createUser();
    UserDTO userDTO = TestUserDataUtil.createUserDTO();
    when(userRepository.findByLogin(any())).thenReturn(Optional.ofNullable(user));
    when(userRepository.save(Objects.requireNonNull(user))).thenReturn(user);

    assertEquals(userDTO, userService.updateUser(userDTO.getLogin(), userDTO));
    verify(userRepository, times(1)).findByLogin(any());
    verify(userRepository, times(1)).save(any());
  }

  @Test
  void updateUserNotFoundCase() {
    UserDTO userDTO = TestUserDataUtil.createUserDTO();
    when(userRepository.findByLogin(any())).thenThrow(EntityNotFoundException.class);

    assertThrows(
        EntityNotFoundException.class, () -> userService.updateUser(userDTO.getLogin(), userDTO));
    verify(userRepository, times(1)).findByLogin(any());
    verify(userRepository, never()).save(any());
  }

  @Test
  void deleteUserTest() {
    doNothing().when(userRepository).deleteById(any());

    userService.deleteUser(any());

    verify(userRepository, times(1)).deleteById(any());
  }
}
