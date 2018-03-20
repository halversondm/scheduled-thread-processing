package com.halversondm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import static java.lang.Thread.sleep;

@Component
public class DumbTaskScheduler implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(DumbTaskScheduler.class);

    @Autowired
    ThreadPoolTaskExecutor dumbTaskExecutor;

    ApplicationContext applicationContext;

    @Scheduled(fixedDelay = 60000)
    public void process() {

        LOGGER.info("Start DumbTaskScheduler");

        List<String> dumbStringList = new ArrayList<>();
        final List<Future> futureList = new ArrayList<>();
        for (int i = 0; i < 70; i++) {
            dumbStringList.add("Hello " + i);
        }

        dumbStringList.forEach(s -> {
            LOGGER.info("executing message # {}", s);
            DumbTask dumbTask = (DumbTask) applicationContext.getBean("dumbTask", s);
            Future future = dumbTaskExecutor.submit(dumbTask);
            futureList.add(future);
        });

        while (true) {
            int count = 0;
            for (Future f : futureList) {
                if (!f.isDone()) {
                    count++;
                }
            }
            if (count == 0) {
                LOGGER.info("Threads are done.  Breaking loop");
                break;
            } else {
                LOGGER.info("Threads are not done.  Active count {}", count);
                try {
                    sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        LOGGER.info("Stop DumbTaskScheduler");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
