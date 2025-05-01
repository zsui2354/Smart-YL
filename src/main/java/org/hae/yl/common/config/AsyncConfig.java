package org.hae.yl.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

//    @Override
//    public Executor getAsyncExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(10);
//        executor.setMaxPoolSize(100);
//        executor.setQueueCapacity(100);
//        executor.setThreadNamePrefix("async-thread-");
//        executor.setWaitForTasksToCompleteOnShutdown(true);  // 设置关闭时等待任务完成
//        executor.setAwaitTerminationSeconds(60);  // 等待最多60秒来终止线程池
//        executor.initialize();
//        return executor;
//    }

    // 如果需要设置异步方法的超时，可以在@Async注解上进行设置
    // @Async(timeout = 60000)   // 设置单个异步方法的超时
}