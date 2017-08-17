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
public class Start {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) {
        new Start().run();
    }

//    private ConcurrentLinkedQueue<Task> queue;
//    private PriorityQueue<Task> priorityQueue;
    private Queue queue;


    private void run() {

        queue = new Queue();
        Exec e = new Exec(queue);
        e.start();


//
        new Producer(queue, "first", "22:25:00", 2).start();
        delay(10000);
        new Producer(queue, "second", "22:25:00", 3).start();
        delay(100001);
        new Producer(queue, "third", "22:25:00", 4).start();
        delay(100002);
        new Producer(queue, "fourth", "22:25:00", 5).start();
        delay(100003);
        new Producer(queue, "fifth", "22:25:00", 6).start();
//        delay();
//        delay();
//        delay();
//        Iterator<Task> iterator = queue.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next().getName());
//        }


//        priorityQueue = new PriorityQueue<>(Comparator.comparing(Task::getTime));
//
////        LocalDateTime.parse("10:00:00", formatter);
//
////        LocalDateTime time = LocalDateTime.of(LocalDate.now(), LocalTime.parse("20:00:00", formatter));
//
//        Task task1 = new Task(LocalDateTime.of(LocalDate.now(), LocalTime.parse("20:00:00", formatter)));
//        Task task2 = new Task(getTime("20:30:00"));
//        Task task3 = new Task(LocalDateTime.of(LocalDate.now(), LocalTime.parse("20:30:02", formatter)));
//
////        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME));
//
//        priorityQueue.add(task2);
//        priorityQueue.add(task3);
//        priorityQueue.add(task1);
//
////        Iterator<Task> iterator = priorityQueue.iterator();
//        while (priorityQueue.peek() != null) {
//            Task task = priorityQueue.poll();
//            System.out.println(task.getTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
//
//        }
    }


    private LocalDateTime getTime(String time) {
        return LocalDateTime.of(LocalDate.now(), LocalTime.parse(time, formatter));
    }

    private void delay(int count) {
        int i = 0;
        for (int j = 0; j < 100000; j++) {
            for (int k = 0; k < count; k++) {
                i = j + k;
            }

        }
        System.out.println(i);
    }



}
