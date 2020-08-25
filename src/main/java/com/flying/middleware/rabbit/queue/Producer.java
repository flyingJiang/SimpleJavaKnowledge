package com.flying.middleware.rabbit.queue;

class Producer extends Thread {
    //生产数量
    private int num;
    //仓库
    private AbstractStorage abstractStorage;

    public Producer(AbstractStorage abstractStorage,int num){
        this.abstractStorage=abstractStorage;
        this.num=num;
    }
    // 调用仓库Storage的生产函数
    public void produce(int num){
        abstractStorage.producer(num);
    }
    // 线程run函数
    @Override
    public void run(){
        produce(num);
    }

}
