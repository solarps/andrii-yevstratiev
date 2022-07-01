package org.epam.spring.homework3.HW3.service.model;

import lombok.Data;

@Data
public class Activity implements Cloneable {
    private String id;
    private String name;
    private String spentTime;
    private String category;

    @Override
    public Activity clone() {
        try {
            Activity clone = (Activity) super.clone();
            clone.setId(id);
            clone.setName(name);
            clone.setSpentTime("00:00");
            clone.setCategory(category);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
