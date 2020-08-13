package com.flying.basicKnowledge.stream;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Before;

public class Test {
    private List<Instalment> list = new ArrayList<>();
    private BigDecimal ratio = BigDecimal.valueOf(0.9);

    @Before
    public void init(){
        for (int j = 1; j< 3; j++) {
            for (int i = 1; i < 11; i++) {
                Instalment instalment = new Instalment();
                instalment.setNumInstalment(Long.valueOf(i));
                instalment.setVersion(Integer.valueOf(j));
                instalment.setStatus("n");
                list.add(instalment);
            }
        }
        Instalment instalment = new Instalment();
        instalment.setNumInstalment(Long.valueOf(1));
        instalment.setVersion(Integer.valueOf(4));
        instalment.setStatus("n");
        list.add(instalment);
        instalment = new Instalment();
        instalment.setNumInstalment(Long.valueOf(1));
        instalment.setVersion(Integer.valueOf(5));
        instalment.setStatus("a");
        list.add(instalment);
    }

    @org.junit.Test
    public void test1(){
        //get version
        List<Instalment> listGetVersion = (List<Instalment>) list.stream().filter(distinctByKey(instalment -> instalment.getVersion())).collect(Collectors.toList());
        Set<Integer> version = list.stream().map(Instalment::getVersion).collect(Collectors.toSet());
        for (Instalment pre : listGetVersion) {
            Optional<Instalment> lastTerm = list.stream().filter(instalment -> instalment.getVersion().equals(pre.getVersion())).max(Comparator.comparingLong(Instalment::getNumInstalment));
            if (lastTerm.get().getStatus().equals("n")) {
                List<Instalment> instalmentList = list.stream().filter(instalment -> instalment.getVersion().equals(pre.getVersion())).collect(Collectors.toList());
                BigDecimal instalmentTotalMoney = instalmentList.stream().map(Instalment::getValueInstalment).reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal instalmentRationTotalMoney = instalmentList.stream().filter(instalment -> instalment.getNumInstalment().intValue() < lastTerm.get().getNumInstalment().intValue())
                        .map(Instalment::getValueInstalmentRatio).reduce(BigDecimal.ZERO, BigDecimal::add);
                instalmentTotalMoney = instalmentTotalMoney.multiply(ratio).setScale(2, RoundingMode.HALF_UP);
                instalmentTotalMoney = instalmentTotalMoney.subtract(instalmentRationTotalMoney);
                final BigDecimal finalInstalmentTotalMoney = instalmentTotalMoney;
                list.stream().filter(instalment -> (instalment.getNumInstalment().equals(lastTerm.get().getNumInstalment()) && instalment.getVersion().equals(lastTerm.get().getVersion())))
                        .collect(Collectors.toList()).get(0).setValueInstalmentRatio(finalInstalmentTotalMoney);
            }
        }

    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
