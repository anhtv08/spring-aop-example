package com.example.springaopexample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RunningTaskBuilder {

    @Bean
    @Scope("prototype")
    RunningTask build(){
        return new RunningTask();
    }
}
