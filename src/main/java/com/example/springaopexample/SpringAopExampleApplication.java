package com.example.springaopexample;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import(RunningTaskBuilder.class)
public class SpringAopExampleApplication  implements CommandLineRunner {

    @Autowired AppService appService;
    @Autowired ExecutorService executorService;

    public static void main(String[] args) {
        SpringApplication.run(SpringAopExampleApplication.class, args);

    }

    @Bean
    ExecutorService taskExecutor(){
        return Executors.newFixedThreadPool(5);

    }

    @Override
    public void run(String... strings) throws Exception {
        appService.performSomeTask();
    }

    @Aspect
    @Configuration
    class PerformanceMeasurement  {

        @Around("@annotation(MethodTime)")
        public void loggingTime (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

            long start = System.currentTimeMillis();
            System.out.println(proceedingJoinPoint.getTarget());
            proceedingJoinPoint.proceed();

            long end = System.currentTimeMillis();
            System.out.println("time consumed :" + (end-start));

        }
    }



}
