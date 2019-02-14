package com.task.taskservice;

import com.task.taskservice.Task;
import com.task.taskservice.TaskStatus;
import com.task.taskservice.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TaskService {
    private TaskRepository repository;

    public TaskService(TaskRepository repository) { this.repository = repository; }

       String createTask() {
        Task task = new Task(UUID.randomUUID().toString(), LocalDateTime.now(), TaskStatus.CREATED);
        repository.save(task);
        return task.getGuid();
    }

       Iterable<Task> getAllTasks() {
        return repository.findAll();
    }
}
