package com.halversondm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static java.lang.Thread.sleep;

/**
 * Point is to run for 30 seconds
 */
@Component
@Scope("prototype")
public class DumbTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(DumbTask.class);

    String message;

    public DumbTask(String message) {
        this.message = message;
    }

    public void run() {

        LOGGER.info("Start {}", message);
        try {
            sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOGGER.info("Stop {}", message);
    }
}
