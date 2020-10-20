package com.flying.basicKnowledge.spi;

import java.util.ServiceLoader;

/**
 * @author Flying.Jiang
 */
public class Main {
    public static void main(String[] args) {
        ServiceLoader<Car> cars = ServiceLoader.load(Car.class);
        for (Car c:cars){
            c.print(c.getClass().toString());
        }
    }
}
