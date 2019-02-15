package com.task.taskservice;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Task {
    @Id
    private String guid;
    private String dateTime;
    private TaskStatus status;

    public Task() { }

    public Task(String guid, String dateTime, TaskStatus status) {
        this.guid = guid;
        this.dateTime = dateTime;
        this.status = status;
    }

    public String getGuid() { return guid; }
    public void setGuid(String guid) { this.guid = guid; }
    public String getDateTime() { return dateTime; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }
    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

}
