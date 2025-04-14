package com.workmanager.controller;

import com.workmanager.model.TaskPriority;
import com.workmanager.model.projection.TaskPriorityProjection;
import com.workmanager.service.TaskPriorityService;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks/priorities")
@CrossOrigin(origins = "*")
public class TaskPriorityController {

    private TaskPriorityService taskPriorityService;

    public TaskPriorityController(TaskPriorityService taskPriorityService) {
        this.taskPriorityService = taskPriorityService;
    }

    @GetMapping("/list")
    public List<TaskPriorityProjection> getAllTaskPriorities() {
        return taskPriorityService.getAllTaskPriorities();
    }

    @GetMapping("/{id}")
    public TaskPriority getTaskPriorityById(Long id) {
        return taskPriorityService.getTaskPriorityById(id);
    }

    @PostMapping("/add")
    public TaskPriority createTaskPriority(@Validated @RequestBody TaskPriorityProjection taskPriority) {
        return taskPriorityService.createTaskPriority(taskPriority);
    }

    @PutMapping("/{id}")
    public TaskPriority updateTaskPriority(Long id, TaskPriority taskPriority) {
        return taskPriorityService.updateTaskPriority(id, taskPriority);
    }

    @DeleteMapping("/{id}")
    public void deleteTaskPriority(Long id) {
        taskPriorityService.deleteTaskPriority(id);
    }
}
