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
    private String schedule;
    private int seconds;

    public Producer(Queue queue, String msg, String schedule) {
        this.queue = queue;
        this.msg = msg;
        this.schedule = schedule;
    }
    public Producer(Queue queue, String msg, String schedule, int seconds) {
        this(queue, msg, schedule);
        this.seconds = seconds;
    }

    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(seconds * 1000);
            Task task = new Task(getTime(schedule));
            task.setName(msg);
            task.setCallable(() -> {
                print(msg + " !!!!");
                return "";
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
