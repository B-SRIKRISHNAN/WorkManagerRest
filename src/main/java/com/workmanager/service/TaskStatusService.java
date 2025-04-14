package com.workmanager.service;

import com.workmanager.model.TaskStatus;
import com.workmanager.model.projection.TaskStatusProjection;
import com.workmanager.repository.TaskStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskStatusService {


    private final TaskStatusRepository taskStatusRepository;

    public TaskStatusService(TaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    public List<TaskStatusProjection> getAllTaskStatuses(){

        // move to taskstatusprojection
        List<TaskStatus> taskStatuses = taskStatusRepository.findAll();
        return taskStatuses.stream().map(status->{
            TaskStatusProjection taskStatusProjection = new TaskStatusProjection();
            taskStatusProjection.setId(status.getTaskStatusId());
            taskStatusProjection.setCode(status.getTaskStatusCode());
            taskStatusProjection.setName(status.getTaskStatusName());
            taskStatusProjection.setDescription(status.getTaskStatusDesc());
            return taskStatusProjection;
        }).collect(Collectors.toList());
    }

    public TaskStatus getTaskStatusById(Long id){
        return taskStatusRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public TaskStatus createTaskStatus(TaskStatusProjection taskStatusProjection){

        TaskStatus taskStatus = new TaskStatus();
        taskStatus.setTaskStatusCode(taskStatusProjection.getCode());
        taskStatus.setTaskStatusName(taskStatusProjection.getName());
        taskStatus.setTaskStatusDesc(taskStatusProjection.getDescription());

        return taskStatusRepository.save(taskStatus);
    }

    public TaskStatus updateTaskStatus(Long id, TaskStatus taskStatus){
        TaskStatus existingTaskStatus = getTaskStatusById(id);
        existingTaskStatus.setTaskStatusCode(taskStatus.getTaskStatusCode());
        existingTaskStatus.setTaskStatusName(taskStatus.getTaskStatusName());
        existingTaskStatus.setTaskStatusDesc(taskStatus.getTaskStatusDesc());
        return taskStatusRepository.save(existingTaskStatus);
    }

    public void deleteTaskStatus(Long id){
        taskStatusRepository.deleteById(id);
    }
}
