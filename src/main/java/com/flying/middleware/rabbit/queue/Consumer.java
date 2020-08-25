package com.flying.middleware.rabbit.queue;

class Consumer extends Thread {
    //消费数量
    private int num;
    //仓库
    private AbstractStorage abstractStorage;

    public Consumer(AbstractStorage abstractStorage,int num){
        this.abstractStorage=abstractStorage;
        this.num=num;
    }

    public void consume(int num){
        abstractStorage.consumer(num);
    }

    @Override
    public void run(){
        consume(num);
    }

}
