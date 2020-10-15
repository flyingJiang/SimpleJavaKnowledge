package com.flying.thread.auxiliary;


import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * https://blog.csdn.net/koobee1/article/details/79606816?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase
 * https://blog.csdn.net/liangyihuai/article/details/83106584
 *
 * @author Flying.Jiang
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(4);
        for (int i = 0; i < latch.getCount(); i++) {
            new Thread(new MyThread(latch), "player" + i).start();
        }
        System.out.println("正在等待所有玩家准备好");
        latch.await();
        System.out.println("开始游戏");
    }

    private static class MyThread implements Runnable {
        private CountDownLatch latch;

        public MyThread(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Random rand = new Random();
                int randomNum = rand.nextInt((3000 - 1000) + 1) + 1000;//产生1000到3000之间的随机整数
                Thread.sleep(randomNum);
                System.out.println(Thread.currentThread().getName() + " 已经准备好了, 所使用的时间为 " + ((double) randomNum / 1000) + "s");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
