package com.task.taskservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService service;
    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> createTask() {
        return new ResponseEntity<>(service.createTask(), ACCEPTED);
    }

    @GetMapping
    public Iterable<Task> getTasks() {
        return service.getAllTasks();
    }

 
}
