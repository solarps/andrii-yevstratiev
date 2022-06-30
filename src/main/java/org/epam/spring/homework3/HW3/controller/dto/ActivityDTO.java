package org.epam.spring.homework3.HW3.controller.dto;

import lombok.Data;

@Data
public class ActivityDTO {
    private String id;
    private String name;
    private String spentTime;
    private String category;
}
