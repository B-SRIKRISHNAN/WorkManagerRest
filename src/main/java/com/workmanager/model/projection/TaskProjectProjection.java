package com.workmanager.model.projection;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskProjectProjection {
    
    private Long id;

    @NotNull(message = "Project Description is required")
    @NotBlank(message = "Project Description is required")
    private String description;

    @NotNull(message = "Project Code is required")
    @NotBlank(message = "Project Code is required")
    private String projectCode;

    @NotNull(message = "Project Description is required")
    @NotBlank(message = "Project Description is required")
    private String projectDescription;
}
