package com.workmanager.model.projection;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TaskProjection {

    @NotNull(message = "Task Name is required")
    @NotBlank(message = "Task Name is required")
    private String name;

    @NotNull(message = "Status is required")
    @NotBlank(message = "Status is required")
    private String statusCode;
    
    @NotNull(message = "Priority is required")
    @NotBlank(message = "Priority is required")
    private String priorityCode;
    
    @NotNull(message = "Type is required")
    @NotBlank(message = "Type is required")
    private String typeCode;
    
    @NotNull(message = "Project is required")
    @NotBlank(message = "Project is required")
    private String projectCode;
}
