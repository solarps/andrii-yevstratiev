package org.epam.spring.homework4.HW4.persistance.entity.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserActivityKey implements Serializable {
  @Column(name = "user_id")
  Long userId;

  @Column(name = "activity_id")
  Long activityId;
}
