package org.epam.spring.homework4.HW4.persistance.repository;

import org.epam.spring.homework4.HW4.persistance.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
  @NonNull
  Optional<Activity> findById(@NonNull Long id);
}
