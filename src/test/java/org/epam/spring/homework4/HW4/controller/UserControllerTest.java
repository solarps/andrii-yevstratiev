package org.epam.spring.homework4.HW4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.epam.spring.homework4.HW4.controller.dto.UserDTO;
import org.epam.spring.homework4.HW4.service.UserService;
import org.epam.spring.homework4.HW4.util.TestUserDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired ObjectMapper objectMapper;
  @Autowired private MockMvc mockMvc;
  @MockBean private UserService userService;

  @Test
  void getAllUsersTest() throws Exception {
    List<UserDTO> userDTOList = TestUserDataUtil.createUserDTOList();

    when(userService.listUsers()).thenReturn(userDTOList);

    mockMvc
        .perform(get("/api/v1/user"))
        .andDo(print())
        .andExpectAll(
            status().isOk(),
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$[0].id").value(userDTOList.get(0).getId()));

    verify(userService, times(1)).listUsers();
  }

  @Test
  void getUserByLoginTest() throws Exception {
    UserDTO userDTO = TestUserDataUtil.createUserDTO();

    when(userService.getUserByLogin(userDTO.getLogin())).thenReturn(userDTO);

    mockMvc
        .perform(get("/api/v1/user/" + userDTO.getLogin()))
        .andDo(print())
        .andExpectAll(
            status().isOk(),
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$.login").value(userDTO.getLogin()));

    verify(userService, times(1)).getUserByLogin(any());
  }

  @Test
  void createUserTest() throws Exception {
    UserDTO userDTO = TestUserDataUtil.createUserDTO();

    when(userService.createUser(any())).thenReturn(userDTO);

    mockMvc
        .perform(
            post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
        .andDo(print())
        .andExpectAll(
            status().isCreated(),
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$.id").value(userDTO.getId()),
            jsonPath("$.login").value(userDTO.getLogin()));

    verify(userService, times(1)).createUser(any());
  }

  @Test
  void updateUser() throws Exception {
    UserDTO userDTO = TestUserDataUtil.createUserDTO();

    when(userService.updateUser(eq(userDTO.getLogin()), any())).thenReturn(userDTO);

    mockMvc
        .perform(
            put("/api/v1/user/" + userDTO.getLogin())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
        .andDo(print())
        .andExpectAll(
            status().isOk(),
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$.login").value(userDTO.getLogin()));

    verify(userService, times(1)).updateUser(eq(userDTO.getLogin()), any());
  }

  @Test
  void deleteUser() throws Exception {
    long ID = 1L;
    doNothing().when(userService).deleteUser(any());

    mockMvc
        .perform(delete("/api/v1/user/" + ID))
        .andDo(print())
        .andExpectAll(status().isNoContent());
  }
}
