package com.task.taskservice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "This is not a GUID")
public class NotAGUIDException extends RuntimeException{


}
