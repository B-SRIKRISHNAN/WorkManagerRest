package com.workmanager.controller;

import com.workmanager.model.TaskType;
import com.workmanager.model.projection.TaskTypeProjection;
import com.workmanager.service.TaskTypeService;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks/types")
@CrossOrigin(origins = "*")
public class TaskTypeController {

    private TaskTypeService taskTypeService;

    public TaskTypeController(TaskTypeService taskTypeService) {
        this.taskTypeService = taskTypeService;
    }

    @GetMapping("/list")
    public List<TaskTypeProjection> getAllTaskTypes() {
        
        return taskTypeService.getAllTaskTypes();
    }

    @GetMapping("/{id}")
    public TaskType getTaskTypeById(Long id) {
        return taskTypeService.getTaskTypeById(id);
    }

    @PostMapping("/add")
    public TaskType createTaskType(@Validated @RequestBody TaskTypeProjection taskType) {
        return taskTypeService.createTaskType(taskType);
    }

    @PutMapping("/{id}")
    public TaskType updateTaskType(Long id, TaskType taskType) {
        return taskTypeService.updateTaskType(id, taskType);
    }

    @DeleteMapping("/{id}")
    public void deleteTaskType(Long id) {
        taskTypeService.deleteTaskType(id);
    }
}