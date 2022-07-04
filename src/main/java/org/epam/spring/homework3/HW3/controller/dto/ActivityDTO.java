package org.epam.spring.homework3.HW3.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.epam.spring.homework3.HW3.controller.dto.validategroup.OnCreate;
import org.epam.spring.homework3.HW3.controller.dto.validategroup.OnUpdate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
public class ActivityDTO {
    @JsonProperty(access = READ_ONLY)
    private String id;
    @NotBlank(message = "name shouldn't be empty", groups = {OnCreate.class, OnUpdate.class})
    private String name;
    @Null(message = "spent time should be null", groups = OnCreate.class)
    @NotBlank(message = "name shouldn't be empty", groups = OnUpdate.class)
    private String spentTime;
    @NotBlank(message = "name shouldn't be empty", groups = {OnCreate.class, OnUpdate.class})
    private String category;
}
