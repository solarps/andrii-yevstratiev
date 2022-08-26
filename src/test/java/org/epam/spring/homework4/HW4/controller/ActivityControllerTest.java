package org.epam.spring.homework4.HW4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.epam.spring.homework4.HW4.controller.dto.ActivityDTO;
import org.epam.spring.homework4.HW4.service.ActivityService;
import org.epam.spring.homework4.HW4.util.TestActivityDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@WebMvcTest(ActivityController.class)
class ActivityControllerTest {

  @Autowired private MockMvc mockMvc;
  @MockBean private ActivityService activityService;
  @Autowired private ObjectMapper objectMapper;

  @Test
  void getAllActivity() throws Exception {
    List<ActivityDTO> activityList = TestActivityDataUtil.createActivityDTOList();

    when(activityService.listActivity()).thenReturn(activityList);

    mockMvc
        .perform(get("/api/v1/activity"))
        .andDo(print())
        .andExpectAll(
            status().isOk(),
            content().contentType(APPLICATION_JSON),
            jsonPath("$[0].id").value(activityList.get(0).getId()),
            jsonPath("$[1].id").value(activityList.get(1).getId()));

    verify(activityService, times(1)).listActivity();
  }

  @Test
  void createActivity() throws Exception {
    ActivityDTO activityDTO = TestActivityDataUtil.createActivityDTO();
    when(activityService.createActivity(any())).thenReturn(activityDTO);

    mockMvc
        .perform(
            post("/api/v1/activity")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(activityDTO)))
        .andDo(print())
        .andExpectAll(
            status().isCreated(),
            content().contentType(APPLICATION_JSON),
            jsonPath("$.id").value(activityDTO.getId()));

    verify(activityService, times(1)).createActivity(any());
  }

  @Test
  void getActivityById() throws Exception {
    Long ID = 3L;
    ActivityDTO activityDTO = TestActivityDataUtil.createActivityDTO();
    activityDTO.setId(ID);

    when(activityService.getActivity(ID)).thenReturn(activityDTO);

    mockMvc
        .perform(get("/api/v1/activity/" + ID))
        .andDo(print())
        .andExpectAll(
            status().isOk(), content().contentType(APPLICATION_JSON), jsonPath("$.id").value(ID));

    verify(activityService, times(1)).getActivity(ID);
  }

  @Test
  void updateActivity() throws Exception {
    long ID = 1L;
    ActivityDTO activityDTO = TestActivityDataUtil.createActivityDTO();
    ActivityDTO oldActivityDTO = TestActivityDataUtil.createActivityDTO();
    oldActivityDTO.setName("Old activity");
    when(activityService.updateActivity(any(), any())).thenReturn(oldActivityDTO);

    mockMvc
        .perform(
            put("/api/v1/activity/" + ID)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(activityDTO)))
        .andDo(print())
        .andExpectAll(
            status().isOk(),
            content().contentType(APPLICATION_JSON),
            jsonPath("$.name").value(oldActivityDTO.getName()));

    verify(activityService, times(1)).updateActivity(any(), any());
  }

  @Test
  void deleteActivity() throws Exception {
    long ID = 1L;
    doNothing().when(activityService).deleteActivity(any());

    mockMvc
        .perform(delete("/api/v1/activity/" + ID))
        .andDo(print())
        .andExpect(status().isNoContent());
  }
}
