package com.company;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("\\");

        Factory factory = context.getBean(Factory.class);
        factory.setInputStream(System.in);
        factory.setLinesCount(2);
        try {
            factory.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
