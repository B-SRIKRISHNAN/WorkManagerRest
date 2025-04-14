package com.workmanager.controller;

import com.workmanager.model.Task;
// import com.workmanager.model.TaskStatus;
// import com.workmanager.model.TaskPriority;
// import com.workmanager.model.TaskType;
import com.workmanager.model.Project;
import com.workmanager.model.projection.TaskProjection;
import com.workmanager.repository.ProjectRepository;
// import com.workmanager.repository.TaskPriorityRepository;
// import com.workmanager.repository.TaskStatusRepository;
// import com.workmanager.repository.TaskTypeRepository;
import com.workmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    // private final TaskStatusRepository taskStatusRepository;
    // private final TaskPriorityRepository taskPriorityRepository;
    // private final TaskTypeRepository taskTypeRepository;
    private final ProjectRepository projectRepository;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/add")
    public TaskProjection createTask(@Validated @RequestBody TaskProjection taskProjection) throws MethodArgumentNotValidException {
        System.out.println(taskProjection);
        return taskService.createTask(taskProjection);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        if (task.getName() == null || task.getName().isEmpty() || 
            task.getStatus() == null || task.getPriority() == null ||
            task.getType() == null || task.getProject() == null) {
            throw new IllegalArgumentException("All required fields must be provided: name, status, priority, type, and project");
        }
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    // @GetMapping("/statuses")
    // public List<Map<String, String>> getTaskStatuses() {
    //     List<TaskStatus> taskStatuses = taskStatusRepository.findAll();
    //     return taskStatuses.stream().map(status->{
    //         HashMap<String, String> map = new HashMap<>();
    //         map.put("code", status.getTaskStatusCode());
    //         map.put("name", status.getTaskStatusName());
    //         map.put("description", status.getTaskStatusDesc());
    //         return map;
    //     }).collect(Collectors.toList());
    // }

    // @GetMapping("/priorities")
    // public List<Map<String, String>> getTaskPriorities() {
    //     List<TaskPriority> taskPriorities = taskPriorityRepository.findAll();
    //     return taskPriorities.stream()
    //             .map(priority -> {
    //                 HashMap<String, String> map = new HashMap<>();
    //                 map.put("code", priority.getTaskPriorityCode());
    //                 map.put("name", priority.getTaskPriorityName());
    //                 map.put("description", priority.getTaskPriorityDesc());
    //                 return map;
    //             })
    //             .collect(Collectors.toList());
    // }

    // @GetMapping("/types")
    // public List<Map<String, String>> getTaskTypes() {
    //     List<TaskType> taskTypes = taskTypeRepository.findAll();
    //     return taskTypes.stream()
    //             .map(type -> {
    //                 HashMap<String, String> map = new HashMap<>();
    //                 map.put("code", type.getTaskTypeCode());
    //                 map.put("name", type.getTaskTypeName());
    //                 map.put("description", type.getTaskTypeDesc());
    //                 return map;
    //             })
    //             .collect(Collectors.toList());
    // }

    @GetMapping("/projects")
    public List<Map<String, String>> getProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(project -> {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("code", project.getProjectCode());
                    map.put("description", project.getProjectDescription());
                    return map;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/download/csv")
    public ResponseEntity<String> downloadTasksAsCsv() {
        List<Task> tasks = taskService.getAllTasks();
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("ID,Name,Description,Status,Priority,Type,Project,Progress,Details\n");
        
        for (Task task : tasks) {
            csvBuilder.append(String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s\n",
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getType(),
                task.getProject(),
                task.getProgressLevel() != null ? task.getProgressLevel() : "N/A",
                task.getProgressDetails() != null ? task.getProgressDetails() : "N/A"
            ));
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=tasks.csv");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csvBuilder.toString());
    }
}
