package ru.davist.scheduler;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 16/08/17
 *
 * @author danil
 */
public class Queue {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static PriorityQueue<Task> queue;
    private ReentrantLock lock;

    public Queue() {
        queue = new PriorityQueue<>(new TimeComparator());
        lock = new ReentrantLock();
    }

    public synchronized void add(Task task) {
        lock.lock();
        LocalDateTime now = LocalDateTime.now();
        print(task.getName() + " " + now.format(DateTimeFormatter.ISO_TIME));
        task.setCreate(now);
        queue.add(task);
        Iterator<Task> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getName());
        }
        lock.unlock();
    }

    public Task checkAndPoll() {
        lock.lock();
        Task task = queue.peek();
        if (task != null) {
            LocalDateTime now = LocalDateTime.now();
            print("check task " + task.getName());
            if (task.getTime().compareTo(now) <= 0) {
//                print("Show Time! ");
                task = queue.poll();
            } else {
                task = null;
            }
        }
        lock.unlock();
        return task;
    }

    private void print(String msg) {
        System.out.println(LocalTime.now().format(formatter) + " - " + msg);
    }

    private static class TimeComparator implements Comparator<Task>    {

        @Override
        public int compare(Task o1, Task o2) {
            int i = o1.getTime().compareTo(o2.getTime());
            if (i == 0) {
                i = o1.getCreate().compareTo(o2.getCreate());
            }
            return i;
        }
    }
}
