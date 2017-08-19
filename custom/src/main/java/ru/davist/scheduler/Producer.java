/*
 * 2017-08-16
 */
package ru.davist.scheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author StarovoytovD
 */
public class Producer implements Runnable {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private Queue queue;
    private String msg;

    /**
     * Время когда должна выполниться задача
     */
    private String triggerTime;

    /**
     * Задержка в секундах перед тем как поток добавит задачу
     */
    private int delay;

    public Producer(Queue queue, String msg, String triggerTime) {
        this.queue = queue;
        this.msg = msg;
        this.triggerTime = triggerTime;
    }
    public Producer(Queue queue, String msg, String triggerTime, int delay) {
        this(queue, msg, triggerTime);
        this.delay = delay;
    }

    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delay * 1000);
            Task task = new Task(getTime(triggerTime));
            task.setName(msg);
            task.setCallable(() -> {
                print("!!!! " + task.getOrderNumber() + " msg: " + msg);
                return msg;
            });

            queue.add(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private LocalDateTime getTime(String time) {
        return LocalDateTime.of(LocalDate.now(), LocalTime.parse(time, formatter));
    }

    private void print(String msg) {
        System.out.println(LocalTime.now().format(formatter) + " - " + msg);
    }
}
