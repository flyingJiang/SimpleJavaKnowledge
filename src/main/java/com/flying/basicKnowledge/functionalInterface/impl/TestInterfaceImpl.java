package com.flying.basicKnowledge.functionalInterface.impl;

import com.flying.basicKnowledge.functionalInterface.TestInterface;
import org.springframework.stereotype.Service;

@Service
public class TestInterfaceImpl implements TestInterface {
    @Override
    public void get() {
        System.out.println("TestInterfaceImpl get");

    }

//    @Override
//    public void set() {
//        System.out.println("TestInterfaceImpl set");
//
//    }
}
