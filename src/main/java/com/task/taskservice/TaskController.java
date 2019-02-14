package com.task.taskservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
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

    @RequestMapping(path = "/{id}", method = GET)
    public ResponseEntity<Task> getTasks(@PathVariable("id") String id) {
        if (service.taskExists(id)) {
            return new ResponseEntity<>(service.getAllTasks(id), HttpStatus.OK);
        } else if (!id.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }




  /*  @RequestMapping(path = "/{id}", method = GET)
    public ResponseEntity<?> getTasks(@PathVariable("id") String id) {
        if (service.getAllTasks(id).getGuid()!=null) {
            return new ResponseEntity<>(service.getAllTasks(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }*/





  /*  @RequestMapping(path = "/{id}", method = GET)
    public Task getTasks(@PathVariable("id") String id) {

         return   service.getAllTasks(id);

    }*/


}
