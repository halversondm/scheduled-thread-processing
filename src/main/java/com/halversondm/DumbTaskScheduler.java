package com.halversondm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DumbTaskScheduler implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(DumbTaskScheduler.class);

    @Autowired
    TaskExecutor dumbTaskExecutor;

    ApplicationContext applicationContext;

    @Scheduled(fixedDelay = 60000)
    public void process() {

        LOGGER.info("Start DumbTaskScheduler");

        List<String> dumbStringList = new ArrayList<>();
        for (int i = 0; i < 70; i++) {
            dumbStringList.add("Hello " + i);
        }

        dumbStringList.forEach(s -> {
            Runnable runnable = (Runnable) applicationContext.getBean("dumbTask", s);
            dumbTaskExecutor.execute(runnable);
        });

        LOGGER.info("Stop DumbTaskScheduler");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
