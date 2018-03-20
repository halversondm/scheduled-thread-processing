package com.halversondm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

/**
 * Point is to run for 30 seconds
 */
@Component
@Scope("prototype")
public class DumbTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(DumbTask.class);

    CountDownLatch countDownLatch;

    String message;

    public DumbTask(String message, CountDownLatch countDownLatch) {
        this.message = message;
        this.countDownLatch = countDownLatch;
    }

    public void run() {

        LOGGER.info("Start {}", message);
        try {
            sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
        LOGGER.info("Stop {}", message);

    }
}
