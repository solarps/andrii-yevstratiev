package org.epam.spring.homework3.HW3.service.mapper;

import org.epam.spring.homework3.HW3.controller.dto.ActivityDTO;
import org.epam.spring.homework3.HW3.service.model.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ActivityMapper {

  ActivityMapper instance = Mappers.getMapper(ActivityMapper.class);

  Activity mapToActivity(ActivityDTO activityDTO);

  ActivityDTO mapToActivityDTO(Activity activity);

  List<ActivityDTO> mapToActivityDTOS(List<Activity> activities);
}
