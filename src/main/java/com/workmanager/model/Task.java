package com.workmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Task name is required")
    @NotBlank(message = "Task name is required")
    private String name;
    
    @NotNull(message = "Task status is required")
    @ManyToOne
    @JoinColumn(name = "task_status_id")
    private TaskStatus status;
    
    @NotNull(message = "Task priority is required")
    @ManyToOne
    @JoinColumn(name = "task_priority_id")
    private TaskPriority priority;
    
    @NotNull(message = "Task type is required")
    @ManyToOne
    @JoinColumn(name = "task_type_id")
    private TaskType type;
    
    @NotNull(message = "Project category is required")
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    
    private String description;
    private Integer progressLevel;
    private String progressDetails;
    private boolean isActive;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
