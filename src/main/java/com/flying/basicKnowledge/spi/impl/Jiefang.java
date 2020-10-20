package com.flying.basicKnowledge.spi.impl;

import com.flying.basicKnowledge.spi.Car;

import lombok.extern.slf4j.Slf4j;

// com.flying.basicKnowledge.spi.impl.Jiefang
@Slf4j
public class Jiefang implements Car {
    @Override
    public void print(String name) {
        log.info("This is a {}", name);
    }
}
