package com.workmanager.model.projection;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskPriorityProjection {

    private Long id;

    @NotNull(message = "Task Priority Code is required")
    @NotBlank(message = "Task Priority Code is required")
    private String code;

    @NotNull(message = "Task Priority Name is required")
    @NotBlank(message = "Task Priority Name is required")
    private String name;

    @NotNull(message = "Task Priority Description is required")
    @NotBlank(message = "Task Priority Description is required")
    private String description;
}
