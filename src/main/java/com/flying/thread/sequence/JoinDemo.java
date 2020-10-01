package com.flying.thread.sequence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Flying.Jiang
 */
public class JoinDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(JoinDemo.class);
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<5;i++){
                    LOGGER.info("{}: {}", Thread.currentThread().getName(), i);
                }
            }
        },"t1");
        t1.start();
        t1.join();

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<5;i++){
                    LOGGER.info("{}: {}", Thread.currentThread().getName(), i);
                }
            }
        },"t2");
        t2.start();
        t2.join();
        LOGGER.info("{}",Thread.currentThread().getName());

    }
}
