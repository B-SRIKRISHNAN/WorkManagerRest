package com.workmanager.service;

import com.workmanager.model.Project;
import com.workmanager.model.Task;
import com.workmanager.model.TaskStatus;
import com.workmanager.model.TaskPriority;
import com.workmanager.model.TaskType;
import com.workmanager.model.projection.TaskProjection;
import com.workmanager.repository.TaskPriorityRepository;
import com.workmanager.repository.TaskRepository;
import com.workmanager.repository.TaskStatusRepository;
import com.workmanager.repository.TaskTypeRepository;
import com.workmanager.repository.ProjectRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final TaskPriorityRepository taskPriorityRepository;
    private final TaskTypeRepository taskTypeRepository;
    private final ProjectRepository projectRepository;      

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
    }

    @Transactional
    public TaskProjection createTask(TaskProjection taskProjection) throws MethodArgumentNotValidException{

        Task task = new Task();
        TaskStatus taskStatus = taskStatusRepository.findTaskStatusByTaskStatusCode(taskProjection.getStatusCode())
            .orElseThrow(() -> {
                BindingResult bindingResult = new BeanPropertyBindingResult(taskProjection.getStatusCode(), "taskStatusCode");
                bindingResult.rejectValue(null, "TaskStatusNotFound", "Task status does not exist in the database");
                return new MethodArgumentNotValidException(null, bindingResult);
            });
        TaskPriority taskPriority = taskPriorityRepository.findTaskPriorityByTaskPriorityCode(taskProjection.getPriorityCode())
            .orElseThrow(() -> {
                BindingResult bindingResult = new BeanPropertyBindingResult(taskProjection.getPriorityCode(), "priorityCode");
                bindingResult.rejectValue(null, "TaskPriorityNotFound", "Task priority does not exist in the database");
                return new MethodArgumentNotValidException(null, bindingResult);
            });
        TaskType taskType = taskTypeRepository.findTaskTypeByTaskTypeCode(taskProjection.getTypeCode())
            .orElseThrow(() -> {
                BindingResult bindingResult = new BeanPropertyBindingResult(taskProjection.getTypeCode(), "typeCode");
                bindingResult.rejectValue(null, "TaskTypeNotFound", "Task type does not exist in the database");
                return new MethodArgumentNotValidException(null, bindingResult);
            });
        Project project = projectRepository.findProjectByProjectCode(taskProjection.getProjectCode())
            .orElseThrow(() -> {
                BindingResult bindingResult = new BeanPropertyBindingResult(taskProjection.getProjectCode(), "projectCode");
                bindingResult.rejectValue(null, "ProjectNotFound", "Project does not exist in the database");
                return new MethodArgumentNotValidException(null, bindingResult);
            });
        task.setName(taskProjection.getName());
        task.setStatus(taskStatus);
        task.setPriority(taskPriority);
        task.setType(taskType);
        task.setProject(project);
        task.setActive(true);
        Task newTask = taskRepository.save(task);
        TaskProjection taskProjection1 = new TaskProjection();
        taskProjection1.setName(newTask.getName());
        taskProjection1.setStatusCode(newTask.getStatus().getTaskStatusCode());
        taskProjection1.setPriorityCode(newTask.getPriority().getTaskPriorityCode());
        taskProjection1.setTypeCode(newTask.getType().getTaskTypeCode());
        taskProjection1.setProjectCode(newTask.getProject().getProjectCode());
        return taskProjection1;
    }

    @Transactional
    public Task startTask(Long taskId) {
        Task currentTask = taskRepository.findCurrentTask().orElse(null);
        if (currentTask != null) {
            currentTask.setStatus(new TaskStatus()); // Update TaskStatus object
            taskRepository.save(currentTask);
        }

        Task newTask = getTaskById(taskId);
        newTask.setStatus(new TaskStatus()); // Update TaskStatus object
        return taskRepository.save(newTask);
    }

    @Transactional
    public Task updateTaskStatus(Long taskId, TaskStatus newStatus) {
        Task task = getTaskById(taskId);
        task.setStatus(newStatus);
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTaskProgress(Long taskId, Integer progressLevel, String progressDetails) {
        Task task = getTaskById(taskId);
        task.setProgressLevel(progressLevel);
        task.setProgressDetails(progressDetails);
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(Long id, Task updatedTask) {
        Task task = getTaskById(id);
        
        task.setName(updatedTask.getName());
        task.setStatus(updatedTask.getStatus());
        task.setPriority(updatedTask.getPriority());
        task.setType(updatedTask.getType());
        task.setProject(updatedTask.getProject());
        task.setDescription(updatedTask.getDescription());
        task.setProgressLevel(updatedTask.getProgressLevel());
        task.setProgressDetails(updatedTask.getProgressDetails());
        task.setActive(updatedTask.isActive());

        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    public Task getCurrentTask() {
        return taskRepository.findCurrentTask()
            .orElseThrow(() -> new EntityNotFoundException("No task currently in progress"));
    }

    public void validateTaskProjection(TaskProjection taskProjection) throws IllegalArgumentException {
        if (taskProjection.getName() == null || taskProjection.getName().isEmpty()) {
            throw new IllegalArgumentException("Task name must not be empty.");
        }
        if (taskProjection.getStatusCode() == null || taskProjection.getStatusCode().isEmpty()) {
            throw new IllegalArgumentException("Task status code must not be empty.");
        }else{
            taskStatusRepository.findTaskStatusByTaskStatusCode(taskProjection.getStatusCode())
                .orElseThrow(() -> new IllegalArgumentException("Task status not found with code: " + taskProjection.getStatusCode()));
        }
        if (taskProjection.getPriorityCode() == null || taskProjection.getPriorityCode().isEmpty()) {
            throw new IllegalArgumentException("Task priority code must not be empty.");
        }else{
            taskPriorityRepository.findTaskPriorityByTaskPriorityCode(taskProjection.getPriorityCode())
                .orElseThrow(() -> new IllegalArgumentException("Task priority not found with code: " + taskProjection.getPriorityCode()));
        }
        if (taskProjection.getTypeCode() == null || taskProjection.getTypeCode().isEmpty()) {
            throw new IllegalArgumentException("Task type code must not be empty.");
        }else{
            taskTypeRepository.findTaskTypeByTaskTypeCode(taskProjection.getTypeCode())
                .orElseThrow(() -> new IllegalArgumentException("Task type not found with code: " + taskProjection.getTypeCode()));
        }
        if (taskProjection.getProjectCode() == null || taskProjection.getProjectCode().isEmpty()) {
            throw new IllegalArgumentException("Task project code must not be empty.");
        }else{
            projectRepository.findProjectByProjectCode(taskProjection.getProjectCode())
                .orElseThrow(() -> new IllegalArgumentException("Project not found with code: " + taskProjection.getProjectCode()));
        }
    }
}
