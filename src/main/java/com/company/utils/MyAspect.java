package com.company.utils;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Class containing AspectJ methods
 */
@Aspect
@EnableAspectJAutoProxy
@Component
public class MyAspect {

    /**
     * This method uses Aspectj to implement the 1 second delay after creating a vehicle
     */
    @AfterReturning("execution(* com.company.command.CreateCommand.interpret(..))")
    void createCommandDelay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
