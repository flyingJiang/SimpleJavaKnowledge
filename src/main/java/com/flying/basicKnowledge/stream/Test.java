package com.flying.basicKnowledge.stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * null 和 new ArrayList<>()的区别？
 *
 */
public class Test {
    public static void main(String[] args) {
        List<Instalment> list = new ArrayList<>();
        list.stream().forEach(instalment -> {
            instalment.setValueInstalmentRatio(BigDecimal.ZERO);
        });
    }
}
