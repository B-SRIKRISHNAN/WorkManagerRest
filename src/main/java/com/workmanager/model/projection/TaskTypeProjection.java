package com.workmanager.model.projection;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskTypeProjection {

    private Long id;

    @NotNull(message = "Task Type Code is required")
    private String code;

    @NotNull(message = "Task Type Name is required")
    private String name;

    private String description;

    // Getters and setters
}
