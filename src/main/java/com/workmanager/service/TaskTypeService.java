package com.workmanager.service;

import com.workmanager.model.TaskType;
import com.workmanager.model.projection.TaskTypeProjection;
import com.workmanager.repository.TaskTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskTypeService {

    @Autowired
    private TaskTypeRepository taskTypeRepository;

    public List<TaskTypeProjection> getAllTaskTypes() {
        List<TaskType> taskTypes = taskTypeRepository.findAll();
        return taskTypes.stream().map(type->{
            TaskTypeProjection taskTypeProjection = new TaskTypeProjection();
            taskTypeProjection.setId(type.getTaskTypeId());
            taskTypeProjection.setCode(type.getTaskTypeCode());
            taskTypeProjection.setName(type.getTaskTypeName());
            taskTypeProjection.setDescription(type.getTaskTypeDesc());
            return taskTypeProjection;
        }).collect(Collectors.toList());
    }

    public TaskType getTaskTypeById(Long id) {
        return taskTypeRepository.findById(id).orElseThrow();
    }

    public TaskType createTaskType(TaskTypeProjection taskTypeProjection) {
        TaskType taskType = new TaskType();
        taskType.setTaskTypeCode(taskTypeProjection.getCode());
        taskType.setTaskTypeName(taskTypeProjection.getName());
        taskType.setTaskTypeDesc(taskTypeProjection.getDescription());
        return taskTypeRepository.save(taskType);
    }

    public TaskType updateTaskType(Long id, TaskType taskType) {
        TaskType existingTaskType = getTaskTypeById(id);
        existingTaskType.setTaskTypeName(taskType.getTaskTypeName());
        existingTaskType.setTaskTypeDesc(taskType.getTaskTypeDesc());
        return taskTypeRepository.save(existingTaskType);
    }

    public void deleteTaskType(Long id) {
        taskTypeRepository.deleteById(id);
    }
}