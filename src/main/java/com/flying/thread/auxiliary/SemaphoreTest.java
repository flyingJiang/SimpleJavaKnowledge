package com.flying.thread.auxiliary;

import java.util.concurrent.Semaphore;

/**
 * @author Flying.Jiang
 */
public class SemaphoreTest {

    public static void main(String[] args){
        int N = 8;
        Semaphore semaphore = new Semaphore(5);   //机器数目
        for(int i=0;i<N;i++) {
            new Worker(i, semaphore).start();
        }
    }
    static class Worker extends Thread{
        private int i;
        private Semaphore semaphore;
        public Worker(int i,Semaphore semaphore){
            this.i = i;
            this.semaphore = semaphore;
        }
        @Override
        public void run(){
            try{
                semaphore.acquire();
                System.out.println("工人"+this.i+"占用一个机器");
                Thread.sleep(1000);
                System.out.println("工人"+this.i+"释放出机器");
                semaphore.release();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
