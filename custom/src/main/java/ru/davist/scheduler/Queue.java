package ru.davist.scheduler;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 16/08/17
 *
 * @author StarovoytovD
 */
public class Queue {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private PriorityQueue<Task> queue;
    private long orderNumber;

    public Queue() {
        queue = new PriorityQueue<>(new TimeComparator());
        orderNumber = 0;
    }

    public synchronized void add(Task task) {
        task.setOrderNumber(orderNumber++);
        print(task.getName() + " " + task.getOrderNumber() + " " + task.getTime().format(DateTimeFormatter.ISO_TIME));
        queue.add(task);
    }

    public Task checkAndPoll() {
        Task task = queue.peek();
        if (task != null && task.getTime().compareTo(LocalDateTime.now()) <= 0) { // Даже если время задачи уже прошло
            synchronized (this) {
                task = queue.poll();
            }
        } else {
            task = null;
        }
        return task;
    }

    private void print(String msg) {
        System.out.println(LocalTime.now().format(formatter) + " - " + msg);
    }

    private static class TimeComparator implements Comparator<Task> {

        @Override
        public int compare(Task o1, Task o2) {
            int i = o1.getTime().compareTo(o2.getTime());
            if (i == 0) {
                i = o1.getOrderNumber() >= o2.getOrderNumber() ? 1 : -1;
            }
            return i;
        }
    }
}
