package com.flying.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * https://www.cnblogs.com/wyq178/p/8965615.html
 * 3.4:compareAndSet的方法原理
 * <p>
 * public final int incrementAndGet(){
 * <p>
 * for(;;){
 * int current=get();
 * int next=current+1;
 * if(compareAndSet(current,next)){
 * <p>
 * return next;
 * }
 * }
 * }
 * 可以看出这是在一个无限的for循环里，然后获取当前的值，再给他加1(固定写死的值，每次自增1)。
 * 然后通过comePareandSet把当前的值和通过+1获取的值经过cas设值，这个方法返回一个boolean值，当成功的时候就返回当前的值，
 * 这样就保证了只有一个线程可以设值成功。注意：这里是一个死循环，只有当前值等于设置后的+1的值时，它才会跳出循环。这也证明cas是一个不断尝试的过程
 */
public class TestAtomicInteger {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        boolean isChange = atomicInteger.compareAndSet(2, 3);
        atomicInteger.incrementAndGet();
    }
}

/**
 * ABA问题的根本在于cas在修改变量的时候，无法记录变量的状态，比如修改的次数，否修改过这个变量。
 * 这样就很容易在一个线程将A修改成B时，另一个线程又会把B修改成A,造成casd多次执行的问题。
 * <p>
 * 4.3：AtomicStampReference
 * <p>
 * AtomicStampReference在cas的基础上增加了一个标记stamp，使用这个标记可以用来觉察数据是否发生变化，给数据带上了一种实效性的检验。它有以下几个参数：
 * <p>
 * //参数代表的含义分别是 期望值，写入的新值，期望标记，新标记值
 * public boolean compareAndSet(V expected,V newReference,int expectedStamp,int newStamp);
 * <p>
 * public V getRerference();
 * <p>
 * public int getStamp();
 * <p>
 * public void set(V newReference,int newStamp);
 * 4.4:AtomicStampReference的使用实例
 * <p>
 * 我们定义了一个money值为19，然后使用了stamp这个标记，这样每次当cas执行成功的时候都会给原来的标记值+1。而后来的线程来执行的时候就因为stamp不符合条件而使cas无法成功，这就保证了每次
 */
class AtomicStampReferenceDemo {
    static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19, 0);

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            int stamp = money.getStamp();
            System.out.println("stamp的值是" + stamp);
            new Thread() {         //充值线程
                @Override
                public void run() {
                    while (true) {
                        Integer account = money.getReference();
                        if (account < 20) {
                            if (money.compareAndSet(account, account + 20, stamp, stamp + 1)) {
                                System.out.println("余额小于20元，充值成功，目前余额：" + money.getReference() + "元");
                                break;
                            }
                        } else {
                            System.out.println("余额大于20元,无需充值");
                        }
                    }
                }
            }.start();
        }
        new Thread() {
            @Override
            public void run() {    //消费线程
                for (int j = 0; j < 100; j++) {
                    while (true) {
                        int timeStamp = money.getStamp();//1
                        int currentMoney = money.getReference();//39
                        if (currentMoney > 10) {
                            System.out.println("当前账户余额大于10元");
                            if (money.compareAndSet(currentMoney, currentMoney - 10, timeStamp, timeStamp + 1)) {
                                System.out.println("消费者成功消费10元，余额" + money.getReference());
                                break;
                            }
                        } else {
                            System.out.println("没有足够的金额");
                            break;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            break;
                        }
                    }
                }
            }
        }.start();
    }
}
