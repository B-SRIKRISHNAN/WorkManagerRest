package com.workmanager.repository;

import com.workmanager.model.Task;
import com.workmanager.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByStatus(TaskStatus status);
    
    @Query("SELECT t FROM Task t WHERE t.status.taskStatusCode = 'WIP'")
    Optional<Task> findCurrentTask();
}
