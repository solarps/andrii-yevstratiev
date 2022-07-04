package org.epam.spring.homework3.HW3.controller.dto;

import lombok.Data;
import org.epam.spring.homework3.HW3.controller.dto.validategroup.OnCreate;
import org.epam.spring.homework3.HW3.controller.dto.validategroup.OnUpdate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.List;

@Data
public class UserDTO {
    @NotBlank(message = "name shouldn't be empty", groups = {OnCreate.class, OnUpdate.class})
    private String name;
    @NotBlank(message = "login shouldn't be empty", groups = {OnCreate.class, OnUpdate.class})
    private String login;
    @Null(message = "activities should be null", groups = OnCreate.class)
    private List<ActivityDTO> activities;
}
