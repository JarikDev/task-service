package com.task.taskservice;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Task {
    @Id
    private String guid;
    private LocalDateTime dateTime;
    private TaskStatus status;

    public Task() { }

    public Task(String guid, LocalDateTime dateTime, TaskStatus status) {
        this.guid = guid;
        this.dateTime = dateTime;
        this.status = status;
    }

    public String getGuid() { return guid; }
    public void setGuid(String guid) { this.guid = guid; }
    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

}
