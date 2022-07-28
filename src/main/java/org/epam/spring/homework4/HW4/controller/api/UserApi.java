package org.epam.spring.homework4.HW4.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.epam.spring.homework4.HW4.controller.dto.ActivityDTO;
import org.epam.spring.homework4.HW4.controller.dto.UserDTO;
import org.epam.spring.homework4.HW4.controller.dto.validation.group.OnCreate;
import org.epam.spring.homework4.HW4.controller.dto.validation.group.OnSetTime;
import org.epam.spring.homework4.HW4.controller.dto.validation.group.OnUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "User management API")
@RequestMapping("/api/v1/user")
public interface UserApi {
  @ApiOperation("Get all users")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  List<UserDTO> getAllUsers();

  @ApiOperation("Gel user by login")
  @ResponseStatus(HttpStatus.OK)
  @ApiImplicitParams(
      @ApiImplicitParam(name = "login", paramType = "path", required = true, value = "User login"))
  @GetMapping("/{login}")
  UserDTO getUserByLogin(@PathVariable String login);

  @ApiOperation("Create user")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  UserDTO createUser(@Validated(OnCreate.class) @RequestBody UserDTO userDTO);

  @ApiOperation("Update user")
  @ResponseStatus(HttpStatus.OK)
  @ApiImplicitParams(
      @ApiImplicitParam(name = "login", paramType = "path", required = true, value = "User login"))
  @PutMapping("/{login}")
  UserDTO updateUser(
      @PathVariable String login, @Validated(OnUpdate.class) @RequestBody UserDTO UserDTO);

  @ApiOperation("Delete user")
  @ResponseStatus(HttpStatus.OK)
  @ApiImplicitParams(
      @ApiImplicitParam(name = "login", paramType = "path", required = true, value = "User login"))
  @DeleteMapping("/{login}")
  ResponseEntity<Void> deleteUser(@PathVariable String login);

  @ApiOperation("Add activity for user")
  @ResponseStatus(HttpStatus.OK)
  @ApiImplicitParams({
    @ApiImplicitParam(name = "login", paramType = "path", required = true, value = "User login"),
    @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Activity id")
  })
  @PutMapping("/{login}/activity/{id}")
  UserDTO addActivityForUser(@PathVariable String login, @PathVariable String id);

  @ApiOperation("Set spent time")
  @ResponseStatus(HttpStatus.OK)
  @ApiImplicitParams({
    @ApiImplicitParam(name = "login", paramType = "path", required = true, value = "User login"),
    @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Activity id")
  })
  @PutMapping("/{login}/activity/{id}/setTime")
  UserDTO setActivityTime(
      @PathVariable String login,
      @PathVariable String id,
      @Validated(OnSetTime.class) @RequestBody ActivityDTO activityDTO);

  @ApiOperation("Get user activities")
  @ResponseStatus(HttpStatus.OK)
  @ApiImplicitParams(
      @ApiImplicitParam(name = "login", paramType = "path", required = true, value = "User login"))
  @GetMapping("/{login}/activity")
  List<ActivityDTO> userActivities(@PathVariable String login);
}
