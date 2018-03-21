package com.halversondm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableScheduling
public class DumbTaskConfiguration {

    @Bean
    public ThreadPoolTaskExecutor dumbTaskExecutor(@Value("${threadPool.maxQueue}") Integer maxQueue, @Value("${threadPool.maxPool}") Integer maxPool, @Value("${threadPool.corePool}") Integer corePool) {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setQueueCapacity(maxQueue);
        threadPoolTaskExecutor.setMaxPoolSize(maxPool);
        threadPoolTaskExecutor.setCorePoolSize(corePool);
        threadPoolTaskExecutor.setThreadNamePrefix("dumbTaskExe");
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPoolTaskExecutor;
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(@Value("${scheduler.poolSize}") Integer poolSize){
        ThreadPoolTaskScheduler threadPoolTaskScheduler
                = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(poolSize);
        threadPoolTaskScheduler.setThreadNamePrefix(
                "taskScheduler");
        return threadPoolTaskScheduler;
    }
}
