package com.flying.middleware.rabbit.queue;

import java.util.LinkedList;

public class MyList<T> {
    private LinkedList<T> storage = new LinkedList<>();
    private int statckSize = 2000;
    public synchronized void push(T e){
        storage.addLast(e);
    }
    public synchronized T peek(){
        if (storage!=null && storage.size()>0){
            return storage.peekFirst();
        }
        return null;
    }
    public void remove(){
        storage.removeFirst();
    }
    public boolean empty(){
        return storage.isEmpty();
    }
}
