package org.epam.spring.homework4.HW4.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "login", nullable = false, unique = true)
  private String login;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Subscription> activities;
}
