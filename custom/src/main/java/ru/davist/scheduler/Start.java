/*
 * 2017-08-16
 */
package ru.davist.scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс запускающий приложени
 *
 * @author StarovoytovD
 */
public class Start {

//    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) {
        new Start().run();
    }

    private Queue queue;

    private void run() {

        queue = new Queue();
        new Exec(queue).start();


        String triggerTime = "20:44:00";

        List<Producer> producers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            producers.add(new Producer(queue, String.valueOf(i), triggerTime, 5));
        }

        producers.forEach(Producer::start);
//        new Producer(queue, "first", "20:44:00", 4).start();
//        new Producer(queue, "second", "20:44:00", 4).start();
//        new Producer(queue, "third", "20:44:00", 4).start();
//        new Producer(queue, "fourth", "20:44:00", 4).start();
//        new Producer(queue, "fifth", "20:44:00", 4).start();
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


//    private LocalDateTime getTime(String time) {
//        return LocalDateTime.of(LocalDate.now(), LocalTime.parse(time, formatter));
//    }
//
//    private void delay(int count) {
//        int i = 0;
//        for (int j = 0; j < 100000; j++) {
//            for (int k = 0; k < count; k++) {
//                i = j + k;
//            }
//
//        }
//        System.out.println(i);
//    }


}
