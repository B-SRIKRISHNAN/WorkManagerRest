package com.workmanager.repository;

import com.workmanager.model.TaskStatus;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
    // Add custom query methods if needed
    Optional<TaskStatus> findTaskStatusByTaskStatusCode(String code);
}
