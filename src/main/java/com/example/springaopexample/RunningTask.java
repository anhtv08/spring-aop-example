package com.example.springaopexample;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*@Component
@Scope("prototype")*/
public class RunningTask implements Runnable {

    @Override
    @MethodTime("measurePerformance")
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
