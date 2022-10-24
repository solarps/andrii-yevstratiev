package org.epam.spring.homework4.HW4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.epam.spring.homework4.HW4.controller.dto.ActivityDTO;
import org.epam.spring.homework4.HW4.controller.dto.SpentTimeDTO;
import org.epam.spring.homework4.HW4.controller.dto.SubscriptionDTO;
import org.epam.spring.homework4.HW4.controller.dto.UserDTO;
import org.epam.spring.homework4.HW4.service.SubscriptionService;
import org.epam.spring.homework4.HW4.service.UserService;
import org.epam.spring.homework4.HW4.util.TestActivityDataUtil;
import org.epam.spring.homework4.HW4.util.TestSubscriptionDataUtil;
import org.epam.spring.homework4.HW4.util.TestUserDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SubscriptionController.class)
@AutoConfigureMockMvc
class SubscriptionControllerTest {

  @MockBean SubscriptionService subscriptionService;
  @MockBean UserService userService;
  @Autowired ObjectMapper objectMapper;
  @Autowired MockMvc mockMvc;

  @Test
  void getAllSubscriptionTest() throws Exception {
    List<SubscriptionDTO> list = TestSubscriptionDataUtil.createSubDTOList();
    when(subscriptionService.getAllSubscription()).thenReturn(list);

    mockMvc
        .perform(get("/api/v1/sub"))
        .andDo(print())
        .andExpectAll(
            status().isOk(),
            content().contentType(APPLICATION_JSON),
            jsonPath("$[0].userLogin").value(list.get(0).getUserLogin()),
            jsonPath("$[1].userLogin").value(list.get(0).getUserLogin()));

    verify(subscriptionService, times(1)).getAllSubscription();
  }

  @Test
  void getAllSubscriptionForUserTest() throws Exception {
    List<SubscriptionDTO> list = TestSubscriptionDataUtil.createSubDTOList();
    UserDTO user = TestUserDataUtil.createUserDTO();
    when(userService.getUserByLogin(eq("Admin123"))).thenReturn(user);
    when(subscriptionService.getAllUserSubscription(user.getId())).thenReturn(list);

    mockMvc
        .perform(get("/api/v1/sub/Admin123"))
        .andDo(print())
        .andExpectAll(
            status().isOk(),
            content().contentType(APPLICATION_JSON),
            jsonPath("$[0].userLogin").value(list.get(0).getUserLogin()),
            jsonPath("$[1].userLogin").value(list.get(0).getUserLogin()));

    verify(userService, times(1)).getUserByLogin(any());
    verify(subscriptionService, times(1)).getAllUserSubscription(user.getId());
  }

  @Test
  void addSubscriptionTest() throws Exception {
    UserDTO user = TestUserDataUtil.createUserDTO();
    ActivityDTO activity = TestActivityDataUtil.createActivityDTO();
    SubscriptionDTO subscription = TestSubscriptionDataUtil.createSubDTO();
    when(userService.getUserByLogin(eq("Admin123"))).thenReturn(user);
    when(subscriptionService.addSubscription(user.getId(), activity.getId()))
        .thenReturn(subscription);

    mockMvc
        .perform(post("/api/v1/sub/Admin123/1"))
        .andDo(print())
        .andExpectAll(
            status().isCreated(),
            content().contentType(APPLICATION_JSON),
            jsonPath("$.userLogin").value(user.getLogin()),
            jsonPath("$.activityName").value(activity.getName()));

    verify(userService, times(1)).getUserByLogin(any());
    verify(subscriptionService, times(1)).addSubscription(user.getId(), activity.getId());
  }

  @Test
  void setSpentTimeValidTest() throws Exception {
    SpentTimeDTO spentTime = new SpentTimeDTO("00:15:30");
    UserDTO user = TestUserDataUtil.createUserDTO();
    ActivityDTO activity = TestActivityDataUtil.createActivityDTO();
    SubscriptionDTO subscription = TestSubscriptionDataUtil.createSubDTO();
    subscription.setSpentTime(spentTime.getSpentTime());
    when(userService.getUserByLogin(eq(user.getLogin()))).thenReturn(user);
    when(subscriptionService.setSpentTimeForSubscription(
            user.getId(), activity.getId(), spentTime.getSpentTime()))
        .thenReturn(subscription);

    mockMvc
        .perform(
            patch("/api/v1/sub/Admin123/1/time")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(spentTime)))
        .andDo(print())
        .andExpectAll(
            status().isOk(),
            content().contentType(APPLICATION_JSON),
            jsonPath("$.userLogin").value(user.getLogin()),
            jsonPath("$.activityName").value(activity.getName()));

    verify(userService, times(1)).getUserByLogin(any());
    verify(subscriptionService, times(1)).setSpentTimeForSubscription(any(), any(), any());
  }

  @Test
  void deleteSubscriptionTest() throws Exception {
    UserDTO user = TestUserDataUtil.createUserDTO();
    when(userService.getUserByLogin(eq(user.getLogin()))).thenReturn(user);
    doNothing().when(subscriptionService).deleteSubscription(any(), any());

    mockMvc
        .perform(delete("/api/v1/sub/Admin123/1"))
        .andDo(print())
        .andExpectAll(status().isNoContent());

    verify(userService, times(1)).getUserByLogin(any());
    verify(subscriptionService, times(1)).deleteSubscription(any(), any());
  }
}
