package com.workmanager.model.projection;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Simlar to taskprojection but for taskstatus
 */
@Data
public class TaskStatusProjection {


    private Long id;
    
    @NotNull(message = "Task Status Code is required")
    @NotBlank(message = "Task Status Code is required")
    private String code;

    @NotNull(message = "Task Status Name is required")
    @NotBlank(message = "Task Status Name is required")
    private String name;

    @NotNull(message = "Task Status Description is required")
    @NotBlank(message = "Task Status Description is required")
    private String description;
}
