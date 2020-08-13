package com.flying.com.xiaomi.q6.demo1;

public class SyncThread implements Runnable{
    private static int count;

    public SyncThread() {
        count = 0;
    }
    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static int getCount() {
        return count;
    }
}
