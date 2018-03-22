package com.halversondm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

/**
 * Point is to run for a number of milliseconds to simulate some task in progress.
 */
@Component
@Scope("prototype")
public class DumbTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(DumbTask.class);
    Integer sleepMillis;

    CountDownLatch countDownLatch;

    String message;

    public DumbTask(String message, CountDownLatch countDownLatch, Integer sleepMillis) {
        this.message = message;
        this.countDownLatch = countDownLatch;
        this.sleepMillis = sleepMillis;
    }

    public void run() {

        LOGGER.info("Start {}", message);
        try {
            sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
        LOGGER.info("Stop {}", message);
    }
}
