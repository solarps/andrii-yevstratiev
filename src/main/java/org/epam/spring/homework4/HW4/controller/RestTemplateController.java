package org.epam.spring.homework4.HW4.controller;

import lombok.AllArgsConstructor;
import org.epam.spring.homework4.HW4.controller.dto.UserDTO;
import org.epam.spring.homework4.HW4.controller.dto.validation.group.OnUpdate;
import org.epam.spring.homework4.HW4.service.impl.RestTemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest-template")
@AllArgsConstructor
public class RestTemplateController {
  private RestTemplateService restTemplateService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/user")
  public UserDTO createUser(@RequestBody UserDTO userDTO) {
    return restTemplateService.createUser(userDTO);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/user")
  public List<UserDTO> getAllUsers() {
    return restTemplateService.getAllUsers();
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/user/{login}")
  public ResponseEntity<Void> deleteUser(@PathVariable String login) {
    restTemplateService.deleteUser(login);
    return ResponseEntity.noContent().build();
  }
}
