package com.halversondm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static java.lang.Thread.sleep;

/**
 * Point is to run for a number of milliseconds to simulate some task in progress.
 */
@Component
@Scope("prototype")
public class DumbTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(DumbTask.class);
    Integer sleepMillis;

    String message;

    public DumbTask(String message, Integer sleepMillis) {
        this.message = message;
        this.sleepMillis = sleepMillis;
    }

    public void run() {

        LOGGER.info("Start {}", message);
        try {
            sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Stop {}", message);
    }
}
