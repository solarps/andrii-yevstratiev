package org.epam.spring.homework4.HW4.service.mapper;

import org.epam.spring.homework4.HW4.controller.dto.SubscriptionDTO;
import org.epam.spring.homework4.HW4.persistance.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubscriptionMapper {
  SubscriptionMapper instance = Mappers.getMapper(SubscriptionMapper.class);

  @Mapping(source = "subscription", target = "userLogin", qualifiedByName = "userLogin")
  @Mapping(source = "subscription", target = "activityName", qualifiedByName = "activityName")
  SubscriptionDTO mapToSubscriptionDTO(Subscription subscription);

  @Named("userLogin")
  default String userLogin(Subscription subscription) {
    return subscription.getUser().getLogin();
  }

  @Named("activityName")
  default String activityName(Subscription subscription) {
    return subscription.getActivity().getName();
  }

  List<SubscriptionDTO> mapToSubscriptionDTOS(List<Subscription> subscriptions);
}
