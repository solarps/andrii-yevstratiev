package org.epam.spring.homework4.HW4.service.mapper;

import org.epam.spring.homework4.HW4.controller.dto.UserDTO;
import org.epam.spring.homework4.HW4.service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
  UserMapper instance = Mappers.getMapper(UserMapper.class);

  UserDTO mapToUserDTO(User user);

  User mapToUser(UserDTO userDTO);

  List<UserDTO> mapToUserDTOS(List<User> users);
}
