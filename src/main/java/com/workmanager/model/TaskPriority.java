package com.workmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class TaskPriority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskPriorityId;

    @NotNull(message = "Task Priority Code is required")
    @NotBlank(message = "Task Priority Code is required")
    private String taskPriorityCode;

    @NotNull(message = "Task Priority Name is required")
    @NotBlank(message = "Task Priority Name is required")
    private String taskPriorityName;

    @NotNull(message = "Task Priority Description is required")
    @NotBlank(message = "Task Priority Description is required")
    private String taskPriorityDesc;

    // Getters and Setters
    public Long getTaskPriorityId() {
        return taskPriorityId;
    }

    public void setTaskPriorityId(Long taskPriorityId) {
        this.taskPriorityId = taskPriorityId;
    }

    public String getTaskPriorityCode() {
        return taskPriorityCode;
    }

    public void setTaskPriorityCode(String taskPriorityCode) {
        this.taskPriorityCode = taskPriorityCode;
    }

    public String getTaskPriorityName() {
        return taskPriorityName;
    }

    public void setTaskPriorityName(String taskPriorityName) {
        this.taskPriorityName = taskPriorityName;
    }

    public String getTaskPriorityDesc() {
        return taskPriorityDesc;
    }

    public void setTaskPriorityDesc(String taskPriorityDesc) {
        this.taskPriorityDesc = taskPriorityDesc;
    }
}
