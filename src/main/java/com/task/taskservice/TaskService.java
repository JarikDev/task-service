package com.task.taskservice;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.task.taskservice.TaskStatus.FINISHED;
import static com.task.taskservice.TaskStatus.RUNNING;
import static java.time.LocalDateTime.now;
import static java.util.Optional.ofNullable;

@Slf4j
@Service
public class TaskService {
    Logger log = LoggerFactory.getLogger(TaskService.class);
    private TaskRepository repository;
    private final Deque<String> createdGuids;

    public Deque<String> getCreatedGuids() {
        return createdGuids;
    }

    public TaskService(TaskRepository repository) {
        this.repository = repository;
        createdGuids = new ArrayDeque<>();
    }

    String createTask() {
        LocalDateTime localDateTime=LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = localDateTime.format(formatter);
        Task task = new Task(UUID.randomUUID().toString(), formatDateTime, TaskStatus.CREATED);
        repository.save(task);
        createdGuids.add(task.getGuid());
        return task.getGuid();
    }

    Iterable<Task> getAllTasks() {
        return repository.findAll();
    }


    Task getAllTasks(String id) {
        return repository.findById(id).orElseThrow(NullPointerException::new);
    }
    public boolean taskExists(String id){
        return repository.findById(id).isPresent();
    }

    void updateTaskStatus() {
        String guid = ofNullable(createdGuids.pollFirst()).orElseThrow(() -> {
            log.error("Created task queue is empty");
            return new NullPointerException("Created task queue is empty");
        });

        Task task = updateTask(guid, RUNNING);

        repository.save(task);

        TimerTask timerTask = new TimerTask() {
            public void run() {
                Task task = updateTask(guid, FINISHED);

                repository.save(task);

                log.debug("Task performed on: " + LocalDateTime.now() + "n" + "Thread's name: " + Thread.currentThread().getName());
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 10000L);
    }

    private Task updateTask(String uuid, TaskStatus status) {
        Task task = repository.findById(uuid).orElseThrow(() -> {
            log.error("Task with UUID=" + uuid + "was not found in repository");
            return new NullPointerException("Task with UUID=" + uuid + "was not found in repository");
        });

        task.setDateTime(now().toString());
        task.setStatus(status);

        return task;
    }
}
