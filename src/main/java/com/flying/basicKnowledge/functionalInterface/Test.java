package com.flying.basicKnowledge.functionalInterface;

import com.flying.basicKnowledge.functionalInterface.impl.TestInterfaceImpl;

public class Test {
    public static void main(String[] args) {
        TestInterfaceImpl testInterface = new TestInterfaceImpl();
        testInterface.get();
        testInterface.set();
    }
}
