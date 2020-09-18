package com.flying.basicKnowledge.functionalInterface;

@FunctionalInterface
public interface TestInterface {
    void get();

    default void set() {
        System.out.println("interface set");
    }
    default void setValue() {
        System.out.println("interface setValue");
    }
}
