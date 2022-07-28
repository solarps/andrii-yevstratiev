package org.epam.spring.homework4.HW4.service.impl;

import lombok.RequiredArgsConstructor;
import org.epam.spring.homework4.HW4.controller.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestTemplateService {
  private final String USER_API = "http://localhost:8080/api/v1/user";
  private final RestTemplate restTemplate;

  public List<UserDTO> getAllUsers() {
    UserDTO[] responseArray = restTemplate.getForObject(USER_API, UserDTO[].class);
    if (responseArray == null) {
      return new ArrayList<>();
    }
    return List.of(responseArray);
  }

  public UserDTO createUser(UserDTO userDTO) {
    return restTemplate.postForObject(USER_API, userDTO, UserDTO.class);
  }

  public void deleteUser(String login) {
    String entityUrl = USER_API + "/" + login;
    restTemplate.delete(entityUrl);
  }
}
