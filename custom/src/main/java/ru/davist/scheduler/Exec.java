/*
 * 2017-08-16
 */
package ru.davist.scheduler;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author StarovoytovD
 */
public class Exec implements Runnable {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private Queue queue;

    public Exec(Queue queue) {
        this.queue = queue;
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
                    current.getCallable().call();
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
