/*
 * 2017-08-16
 */
package ru.davist.scheduler;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author StarovoytovD
 */
public class Exec implements Runnable {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private Queue queue;
    private ExecutorService executorService;

    public Exec(Queue queue) {
        this.queue = queue;
        executorService = Executors.newSingleThreadExecutor();
    }

    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            Task current = queue.checkAndPoll();
            if (current != null) {
                try {
                    executorService.submit(current.getCallable());
                    continue;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                print("sleep");
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void print(String msg) {
        System.out.println(LocalTime.now().format(formatter) + " - " + msg);
    }


}
