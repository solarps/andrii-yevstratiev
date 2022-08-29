package org.epam.spring.homework4.HW4.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.epam.spring.homework4.HW4.controller.dto.SpentTimeDTO;
import org.epam.spring.homework4.HW4.controller.dto.SubscriptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Subscription management API")
@RequestMapping("/api/v1/sub")
public interface SubscriptionAPI {
  @ApiOperation("Get all subs")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  List<SubscriptionDTO> getAllSubscription();

  @ApiOperation("Get subs for user")
  @ResponseStatus(HttpStatus.OK)
  @ApiImplicitParams(
      @ApiImplicitParam(name = "login", paramType = "path", required = true, value = "User login"))
  @GetMapping("/{login}")
  List<SubscriptionDTO> getAllSubscriptionForUser(@PathVariable String login);

  @ApiOperation("Add sub for user")
  @ResponseStatus(HttpStatus.CREATED)
  @ApiImplicitParams({
    @ApiImplicitParam(name = "login", paramType = "path", required = true, value = "User login"),
    @ApiImplicitParam(
        name = "activityID",
        paramType = "path",
        required = true,
        value = "Activity id")
  })
  @PostMapping("/{login}/{activityID}")
  SubscriptionDTO addSubscription(@PathVariable String login, @PathVariable Long activityID);

  @ApiOperation("Set spent time")
  @ResponseStatus(HttpStatus.OK)
  @ApiImplicitParams({
    @ApiImplicitParam(name = "login", paramType = "path", required = true, value = "User login"),
    @ApiImplicitParam(
        name = "activityID",
        paramType = "path",
        required = true,
        value = "Activity id")
  })
  @PatchMapping("/{login}/{activityID}/time")
  SubscriptionDTO setSpentTime(
      @PathVariable String login,
      @PathVariable Long activityID,
      @RequestBody SpentTimeDTO spentTimeDTO);

  @ApiOperation("Delete sub")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiImplicitParams({
    @ApiImplicitParam(name = "login", paramType = "path", required = true, value = "User login"),
    @ApiImplicitParam(
        name = "activityID",
        paramType = "path",
        required = true,
        value = "Activity id")
  })
  @DeleteMapping("/{login}/{activityID}")
  ResponseEntity<Void> deleteSubscription(
      @PathVariable String login, @PathVariable Long activityID);
}
