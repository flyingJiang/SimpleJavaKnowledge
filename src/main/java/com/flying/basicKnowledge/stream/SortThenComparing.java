package com.flying.basicKnowledge.stream;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: SimpleJavaKnowledge
 * @description:
 * @author: jiangjianfei
 * @create: 2021-03-05 15:56
 **/
public class SortThenComparing {
    public static void main(String[] args) {
        List<DateEntity> list = new ArrayList<>();
        DateEntity dateEntity = new DateEntity();
        dateEntity.setDateA(LocalDate.of(2021, 10, 10));
        dateEntity.setDateB(LocalDate.of(2021, 10, 11));
        list.add(dateEntity);
        dateEntity = new DateEntity();
        dateEntity.setDateA(LocalDate.of(2022, 10, 10));
        dateEntity.setDateB(LocalDate.of(2021, 10, 11));
        list.add(dateEntity);
        dateEntity = new DateEntity();
        dateEntity.setDateA(LocalDate.of(2021, 10, 10));
        dateEntity.setDateB(LocalDate.of(2022, 10, 11));
        list.add(dateEntity);
        dateEntity = new DateEntity();
        dateEntity.setDateA(LocalDate.of(2022, 10, 10));
        dateEntity.setDateB(LocalDate.of(2022, 10, 11));
        list.add(dateEntity);

        List<DateEntity> list2 = list.stream().sorted(Comparator.comparing(DateEntity::getDateA)
                .thenComparing(DateEntity::getDateB)).collect(Collectors.toList());
        List<DateEntity> list3 = list.stream().sorted(Comparator.comparing(DateEntity::getDateA)
                .thenComparing(DateEntity::getDateB).reversed()).collect(Collectors.toList());
        System.out.println(list.size());

    }
}

@Data
class DateEntity {
    private LocalDate dateA;
    private LocalDate dateB;
}
