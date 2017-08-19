/*
 * 2017-08-16
 */
package ru.davist.scheduler;

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


        new Producer(queue, "2", "20:07:05", 1).start();


        String triggerTime = "20:07:10";
        List<Producer> producers = new ArrayList<>();
        for (int i = 3; i < 8; i++) {
            producers.add(new Producer(queue, String.valueOf(i), triggerTime, 3));
        }
        producers.forEach(Producer::start);

        new Producer(queue, "1", "20:06:10", 2).start();

    }

}
