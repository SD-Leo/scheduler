package ru.davist.scheduler;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

/**
 * 16/08/17
 * <p>
 * Задача, которую нужно выполнить в заданное время
 *
 * @author StarovoytovD
 */
public class Task {

    private LocalDateTime time;

    private Callable<String> callable;

    private String name;

    /**
     * Порядковый номер задачи
     * <p>
     * Вряд ли это поле должно быть в этом объекте
     */
    private long orderNumber;

    public Task() {
    }

    public Task(LocalDateTime time) {
        this();
        this.time = time;
    }

    public Task(LocalDateTime time, Callable<String> callable) {
        this(time);
        this.callable = callable;
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

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }
}
