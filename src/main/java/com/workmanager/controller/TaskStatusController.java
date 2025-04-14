package com.workmanager.controller;

import com.workmanager.model.TaskStatus;
import com.workmanager.model.projection.TaskStatusProjection;
import com.workmanager.service.TaskStatusService;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller is used to handle any CRUD or other operation related to Task Status Model
 */
@RestController
@RequestMapping("/api/tasks/status")
@CrossOrigin(origins = "*")
public class TaskStatusController {

    private TaskStatusService taskStatusService;

    public TaskStatusController(TaskStatusService taskStatusService) {
        this.taskStatusService = taskStatusService;
    }
    /**
     * Get all task statuses
     * @return list of task statuses
     */
    @GetMapping("/list")
    public List<TaskStatusProjection> getAllTaskStatuses() {
        return taskStatusService.getAllTaskStatuses();
    }

    /**
     * Get task status by id
     * @param id task status id
     * @return task status
     */
    @GetMapping("/{id}")
    public TaskStatus getTaskStatusById(Long id) {
        return taskStatusService.getTaskStatusById(id);
    }

    /**
     * Create new task status
     * @param taskStatus task status
     * @return created task status
     */
    @PostMapping("/add")
    public TaskStatus createTaskStatus(@Validated @RequestBody TaskStatusProjection taskStatus) {
        return taskStatusService.createTaskStatus(taskStatus);
    }

    /**
     * Update task status
     * @param id task status id
     * @param taskStatus task status
     * @return updated task status
     */
    @PutMapping("/{id}")
    public TaskStatus updateTaskStatus(Long id, TaskStatus taskStatus) {
        return taskStatusService.updateTaskStatus(id, taskStatus);
    }

    /**
     * Delete task status
     * @param id task status id
     */
    @DeleteMapping("/{id}")
    public void deleteTaskStatus(Long id) {
        taskStatusService.deleteTaskStatus(id);
    }
}
