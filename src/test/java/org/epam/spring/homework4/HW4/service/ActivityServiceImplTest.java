package org.epam.spring.homework4.HW4.service;

import org.epam.spring.homework4.HW4.controller.dto.ActivityDTO;
import org.epam.spring.homework4.HW4.persistance.entity.Activity;
import org.epam.spring.homework4.HW4.persistance.repository.ActivityRepository;
import org.epam.spring.homework4.HW4.service.exception.EntityNotFoundException;
import org.epam.spring.homework4.HW4.service.impl.ActivityServiceImpl;
import org.epam.spring.homework4.HW4.service.mapper.ActivityMapperImpl;
import org.epam.spring.homework4.HW4.util.TestActivityDataUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivityServiceImplTest {

  @InjectMocks private ActivityServiceImpl activityService;
  @Mock private ActivityRepository activityRepository;

  @Test
  void createActivity() {
    ActivityDTO activityDTO = TestActivityDataUtil.createActivityDTO();
    Activity activity = TestActivityDataUtil.createActivity();

    when(activityRepository.save(activity)).thenReturn(activity);

    Assertions.assertEquals(activityDTO, activityService.createActivity(activityDTO));
    verify(activityRepository,times(1)).save(activity);
  }

  @Test
  void getActivityPositiveCase() {
    Activity activity = TestActivityDataUtil.createActivity();
    when(activityRepository.findById(activity.getId())).thenReturn(Optional.of(activity));

    ActivityDTO activityDTO = activityService.getActivity(activity.getId());

    Assertions.assertEquals(activityDTO.getId(), activity.getId());
    verify(activityRepository,times(1)).findById(activity.getId());
  }

  @Test
  void getActivityNegativeCase() {
    when(activityRepository.findById(any()))
        .thenThrow(EntityNotFoundException.class);

    Assertions.assertThrows(EntityNotFoundException.class, () -> activityService.getActivity(1L));
    verify(activityRepository,times(1)).findById(any());
  }

  @Test
  void listActivity() {
    List<Activity> list = TestActivityDataUtil.createActivityList();
    when(activityRepository.findAll()).thenReturn(list);

    assertThat(activityService.listActivity(), hasItem(TestActivityDataUtil.createActivityDTO()));
    verify(activityRepository,times(1)).findAll();
  }

  @Test
  void updateActivityPositiveCase() {
    Activity activity = TestActivityDataUtil.createActivity();
    ActivityDTO activityDTO = TestActivityDataUtil.createActivityDTO();
    activityDTO.setName("New");
    Activity repActivity = ActivityMapperImpl.instance.mapToActivity(activityDTO);

    when(activityRepository.findById(any())).thenReturn(Optional.of(activity));
    when(activityRepository.save(repActivity)).thenReturn(repActivity);

    Assertions.assertEquals(
        activityService.updateActivity(1L, activityDTO),
        ActivityMapperImpl.instance.mapToActivityDTO(activity));
    verify(activityRepository,times(1)).findById(any());
    verify(activityRepository,times(1)).save(repActivity);
  }

  @Test
  void updateActivityNotFoundCase() {
    when(activityRepository.findById(any()))
        .thenThrow(EntityNotFoundException.class);

    Assertions.assertThrows(
        EntityNotFoundException.class,
        () -> activityService.updateActivity(1L, TestActivityDataUtil.createActivityDTO()));
    verify(activityRepository,times(1)).findById(any());
  }

  @Test
  void deleteActivity() {
    doNothing().when(activityRepository).deleteById(any());

    activityService.deleteActivity(1L);

    verify(activityRepository, times(1)).deleteById(1L);
  }
}
