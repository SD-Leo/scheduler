package ru.davist.scheduler;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

/**
 * 16/08/17
 *
 * @author danil
 */
public class Task {

    private LocalDateTime create;

    private LocalDateTime time;

    private Callable<String> callable;

    private String name;

    public Task() {
        this.create = LocalDateTime.now();
    }

    public Task(LocalDateTime time) {
        this();
        this.time = time;
    }

    public Task(LocalDateTime time, Callable<String> callable) {
        this(time);
        this.callable = callable;
    }

    public LocalDateTime getCreate() {
        return create;
    }

    public void setCreate(LocalDateTime create) {
        this.create = create;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Callable<String> getCallable() {
        return callable;
    }

    public void setCallable(Callable<String> callable) {
        this.callable = callable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
