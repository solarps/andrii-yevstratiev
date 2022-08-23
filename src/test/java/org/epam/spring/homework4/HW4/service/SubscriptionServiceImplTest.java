package org.epam.spring.homework4.HW4.service;

import org.epam.spring.homework4.HW4.controller.dto.SubscriptionDTO;
import org.epam.spring.homework4.HW4.persistance.entity.Subscription;
import org.epam.spring.homework4.HW4.persistance.repository.SubscriptionRepository;
import org.epam.spring.homework4.HW4.service.impl.SubscriptionServiceImpl;
import org.epam.spring.homework4.HW4.util.TestSubscriptionDataUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceImplTest {

  @InjectMocks private SubscriptionServiceImpl subscriptionService;

  @Mock private SubscriptionRepository subscriptionRepository;

  @Test
  void addSubscriptionTest() {
    Subscription subscription = TestSubscriptionDataUtil.createSub();
    SubscriptionDTO subscriptionDTO = TestSubscriptionDataUtil.createSubDTO();
    doNothing()
        .when(subscriptionRepository)
        .addSubscription(subscription.getUser().getId(), subscription.getActivity().getId());
    when(subscriptionRepository.getById(any())).thenReturn(subscription);

    assertEquals(
        subscriptionDTO,
        subscriptionService.addSubscription(
            subscription.getUser().getId(), subscription.getActivity().getId()));
    verify(subscriptionRepository, times(1)).addSubscription(any(), any());
    verify(subscriptionRepository, times(1)).getById(any());
  }

  @Test
  void deleteSubscriptionTest() {
    doNothing().when(subscriptionRepository).deleteUserSubscription(any(), any());

    subscriptionService.deleteSubscription(any(), any());

    verify(subscriptionRepository, times(1)).deleteUserSubscription(any(), any());
  }

  @Test
  void getAllUserSubscriptionTest() {
    List<Subscription> subscriptionList = TestSubscriptionDataUtil.createSubList();
    when(subscriptionRepository.getAllSubsForUser(any())).thenReturn(subscriptionList);

    assertThat(
        subscriptionService.getAllUserSubscription(any()),
        hasItem(TestSubscriptionDataUtil.createSubDTO()));

    verify(subscriptionRepository, times(1)).getAllSubsForUser(any());
  }

  @Test
  void setSpentTimeForSubscriptionTest() {
    doNothing().when(subscriptionRepository).setSpentTimeToSubscription(any(), any(), any());
    when(subscriptionRepository.getById(any())).thenReturn(TestSubscriptionDataUtil.createSub());

    assertEquals(
        TestSubscriptionDataUtil.createSubDTO(),
        subscriptionService.setSpentTimeForSubscription(any(), any(), any()));

    verify(subscriptionRepository, times(1)).setSpentTimeToSubscription(any(), any(), any());
    verify(subscriptionRepository, times(1)).getById(any());
  }

  @Test
  void getAllSubscriptionTest() {
    when(subscriptionRepository.findAll()).thenReturn(TestSubscriptionDataUtil.createSubList());

    assertThat(
        subscriptionService.getAllSubscription(),
        hasItem(TestSubscriptionDataUtil.createSubDTO()));
    verify(subscriptionRepository, times(1)).findAll();
  }
}
