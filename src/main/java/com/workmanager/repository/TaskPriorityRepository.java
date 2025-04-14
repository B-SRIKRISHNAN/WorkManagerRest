package com.workmanager.repository;

import com.workmanager.model.TaskPriority;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskPriorityRepository extends JpaRepository<TaskPriority, Long> {
    // Add custom query methods if needed
    Optional<TaskPriority> findTaskPriorityByTaskPriorityCode(String code);
}
