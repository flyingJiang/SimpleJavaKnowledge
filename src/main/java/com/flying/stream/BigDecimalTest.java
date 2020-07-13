package com.flying.stream;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class BigDecimalTest {
    public static void main(String[] args) {
        List<Instalment> instalmentFilterList= new ArrayList<>();
        Instalment instalment;
        for (int i=0;i<9;i++){
            instalment = new Instalment();
            instalment.setValueInstalment(BigDecimal.valueOf(50.09));
            instalment.setValueInstalmentRatio(BigDecimal.valueOf(45.08));
            instalment.setNumInstalment(Long.valueOf(i));
            instalmentFilterList.add(instalment);
        }
        ContractInstalmentCalculationEvent event = new ContractInstalmentCalculationEvent();
        BigDecimal ratio = BigDecimal.valueOf(0.9);
        event.setAmount(BigDecimal.valueOf(50.09));
        event.setInstalmentNumber(10);
        BigDecimal valueInstalment = instalmentFilterList.stream()
                .map(Instalment::getValueInstalment)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(event.getAmount())
                .multiply(ratio).setScale(2, RoundingMode.HALF_UP);
        System.out.println(valueInstalment);

        BigDecimal valueInstalmentRatio = instalmentFilterList.stream()
                .filter(x -> x.getNumInstalment().intValue() < event.getInstalmentNumber())
                .map(Instalment::getValueInstalmentRatio)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(valueInstalmentRatio);
        //valueInstalment.subtract(valueInstalmentRatio)
        System.out.println(valueInstalment.subtract(valueInstalmentRatio));

    }
}
