package org.epam.spring.homework4.HW4.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.epam.spring.homework4.HW4.controller.dto.ActivityDTO;
import org.epam.spring.homework4.HW4.controller.dto.validation.group.OnCreate;
import org.epam.spring.homework4.HW4.controller.dto.validation.group.OnUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Activity management API")
@RequestMapping("/api/v1/activity")
public interface ActivityAPI {
  @ApiOperation("Get all activity")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  List<ActivityDTO> getAllActivity();

  @ApiOperation("Create activity")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  ActivityDTO createActivity(@Validated(OnCreate.class) @RequestBody ActivityDTO activityDTO);

  @ApiOperation("Get activity by id")
  @ResponseStatus(HttpStatus.OK)
  @ApiImplicitParams(
      @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Activity id"))
  @GetMapping("/{id}")
  ActivityDTO getActivityById(@PathVariable Long id);

  @ApiOperation("Update activity")
  @ResponseStatus(HttpStatus.OK)
  @ApiImplicitParams(
      @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Activity id"))
  @PutMapping("/{id}")
  ActivityDTO updateActivity(
      @PathVariable Long id, @Validated(OnUpdate.class) @RequestBody ActivityDTO activityDTO);

  @ApiOperation("Delete activity")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiImplicitParams(
      @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Activity id"))
  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteActivity(@PathVariable Long id);
}
