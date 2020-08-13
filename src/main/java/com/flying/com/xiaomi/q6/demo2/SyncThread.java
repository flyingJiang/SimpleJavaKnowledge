package com.flying.com.xiaomi.q6.demo2;

public class SyncThread implements Runnable{
    private static int count;

    public SyncThread() {
        count = 0;
    }
    @Override
    public void run() {
        String s = Thread.currentThread().getName();
        if (s.equals("A")) {
            countAdd();
            return;
        }
        printCount();
    }

    public void countAdd() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void printCount() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + "count:" + count);
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int getCount() {
        return count;
    }

}
