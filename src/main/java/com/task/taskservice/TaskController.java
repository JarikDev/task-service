package com.task.taskservice;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {
    Logger log= LoggerFactory.getLogger(TaskController.class);
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


    @RequestMapping(path = "/{id}", method = GET)
    public Task getTasks(@PathVariable("id") String id) {
        return service.getAllTasks(id);
    }


}
