package com.halversondm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DumbTaskScheduler implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(DumbTaskScheduler.class);

    private static final Integer[] workTimeMillis = {5000, 10000, 30000};

    @Autowired
    ThreadPoolTaskExecutor dumbTaskExecutor;

    ApplicationContext applicationContext;

    @Scheduled(fixedDelay = 60000)
    public void process() {
        LOGGER.info("Start DumbTaskScheduler");
        final Random random = new Random();
        List<String> dumbStringList = new ArrayList<>();
        for (int i = 0; i < 70; i++) {
            dumbStringList.add("Hello " + i);
        }
        dumbStringList.forEach(s -> {
            LOGGER.info("executing message # {}", s);
            int next = random.nextInt(2);
            Runnable runnable = (Runnable) applicationContext.getBean("dumbTask", s, workTimeMillis[next]);
            dumbTaskExecutor.execute(runnable);
        });
        LOGGER.info("Stop DumbTaskScheduler");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
