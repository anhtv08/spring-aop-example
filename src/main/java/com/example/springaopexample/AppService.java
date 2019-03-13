package com.example.springaopexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;

@Service
public class AppService {

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    ExecutorService executorService;

    public void performSomeTask(){

        for (int i =0 ; i <5; i++){
            RunningTask runningTask = applicationContext.getBean(RunningTask.class);
            executorService.execute(runningTask);
        }

    }
}
