package com.workmanager.repository;

import com.workmanager.model.TaskType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {
    // Add custom query methods if needed
    Optional<TaskType> findTaskTypeByTaskTypeCode(String code);
}
