package com.workmanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workmanager.model.Project;
import com.workmanager.model.projection.TaskProjectProjection;
import com.workmanager.service.TaskProjectService;

@RestController
@RequestMapping("/api/tasks/projects")
public class TaskProjectController {

    private final TaskProjectService taskProjectService;

    public TaskProjectController(TaskProjectService taskProjectService) {
        this.taskProjectService = taskProjectService;
    }

    @GetMapping()
    public List<TaskProjectProjection> getAllProjects() {
        return taskProjectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return taskProjectService.getProjectById(id);
    }

    @PostMapping()
    public Project createProject(@RequestBody Project project) {
        return taskProjectService.createProject(project);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project project) {
        return taskProjectService.updateProject(id, project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        taskProjectService.deleteProject(id);
    }
    
}
