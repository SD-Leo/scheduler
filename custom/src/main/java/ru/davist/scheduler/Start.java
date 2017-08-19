/*
 * 2017-08-16
 */
package ru.davist.scheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс запускающий приложение
 *
 * @author StarovoytovD
 */
public class Start {

    public static void main(String[] args) {
        new Start().run();
    }

    private void run() {

        Queue queue = new Queue();

        new Exec(queue).start();

        String triggerTime = "20:31:10";
        Task longTask = new Task();
        longTask.setTime(LocalDateTime.of(LocalDate.now(), LocalTime.parse(triggerTime, DateTimeFormatter.ofPattern("HH:mm:ss"))));
        longTask.setName("Long task");
        longTask.setCallable(() -> {
            System.out.println("long task");
            Thread.sleep(3000L);
            return "";
        });
        queue.add(longTask);

        new Producer(queue, "task2", "20:31:05", 1).start();

        List<Producer> producers = new ArrayList<>();
        for (int i = 3; i < 8; i++) {
            producers.add(new Producer(queue, "task" + i, triggerTime, 3));
        }
        producers.forEach(Producer::start);

        new Producer(queue, "task1", "20:30:40", 2).start();

    }

}
