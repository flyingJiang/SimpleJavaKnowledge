package com.flying.basicKnowledge.proxy.jdkproxy;

public class RealSubject implements Subject {
    @Override
    public void doSth() {
        System.out.println("RealSubject do something");
    }
}
