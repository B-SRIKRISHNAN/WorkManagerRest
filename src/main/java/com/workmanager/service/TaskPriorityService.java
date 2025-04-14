package com.workmanager.service;

import com.workmanager.model.TaskPriority;
import com.workmanager.model.projection.TaskPriorityProjection;
import com.workmanager.repository.TaskPriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskPriorityService {

    @Autowired
    private TaskPriorityRepository taskPriorityRepository;

    public List<TaskPriorityProjection> getAllTaskPriorities() {
        // move to taskpriorityprojection
        List<TaskPriority> taskPriorities = taskPriorityRepository.findAll();
        return taskPriorities.stream().map(prior->{
            TaskPriorityProjection taskPriorityProjection = new TaskPriorityProjection();
            taskPriorityProjection.setId(prior.getTaskPriorityId());
            taskPriorityProjection.setCode(prior.getTaskPriorityCode());
            taskPriorityProjection.setName(prior.getTaskPriorityName());
            taskPriorityProjection.setDescription(prior.getTaskPriorityDesc());
            return taskPriorityProjection;
        }).collect(Collectors.toList());
    }

    public TaskPriority getTaskPriorityById(Long id) {
        return taskPriorityRepository.findById(id).orElseThrow();
    }

    public TaskPriority createTaskPriority(TaskPriorityProjection taskPriorityProjection) {
        TaskPriority taskPriority = new TaskPriority();
        taskPriority.setTaskPriorityCode(taskPriorityProjection.getCode());
        taskPriority.setTaskPriorityName(taskPriorityProjection.getName());
        taskPriority.setTaskPriorityDesc(taskPriorityProjection.getDescription());
        return taskPriorityRepository.save(taskPriority);
    }

    public TaskPriority updateTaskPriority(Long id, TaskPriority taskPriority) {
        TaskPriority existingTaskPriority = getTaskPriorityById(id);
        existingTaskPriority.setTaskPriorityName(taskPriority.getTaskPriorityName());
        existingTaskPriority.setTaskPriorityDesc(taskPriority.getTaskPriorityDesc());
        return taskPriorityRepository.save(existingTaskPriority);
    }

    public void deleteTaskPriority(Long id) {
        taskPriorityRepository.deleteById(id);
    }
}