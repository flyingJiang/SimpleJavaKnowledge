package com.flying.middleware.rabbit.queue;

public interface AbstractStorage {
    void consumer(int num);
    void producer(int num);
}
