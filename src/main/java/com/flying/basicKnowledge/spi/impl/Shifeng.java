package com.flying.basicKnowledge.spi.impl;

import com.flying.basicKnowledge.spi.Car;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Shifeng implements Car {
    @Override
    public void print(String name) {
        log.info("This is a {}", name);
    }
}
