package com.workmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.workmanager.model.Project;
import com.workmanager.model.projection.TaskProjectProjection;
import com.workmanager.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskProjectService {
    
    private final ProjectRepository projectRepository;

    public List<TaskProjectProjection> getAllProjects() {
        return projectRepository.findAll().stream().map(project -> {
            TaskProjectProjection taskProjectProjection = new TaskProjectProjection();
            taskProjectProjection.setId(project.getProjectId());
            taskProjectProjection.setProjectCode(project.getProjectCode());
            taskProjectProjection.setDescription(project.getProjectDescription());
            return taskProjectProjection;
        }).collect(Collectors.toList());
    }


    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project project) {
        Project existingProject = getProjectById(id);
        existingProject.setProjectDescription(project.getProjectDescription());
        existingProject.setProjectCode(project.getProjectCode());
        return projectRepository.save(existingProject);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }   
    
}
