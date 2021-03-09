package com.flying.basicKnowledge.stream;

import lombok.Data;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: SimpleJavaKnowledge
 * @description:
 * @author: jiangjianfei
 * @create: 2021-03-08 09:57
 **/
public class SortThenComparingTest {
    private static List<DateEntity> list;

    @BeforeClass
    public static void init() {
        list = new ArrayList<>();
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
    }

    @Test
    public void sortTest() {
        // 按照 dateA 排序，如果相同，在按照 dateB 排序 （从过去到现在，过去的时间排在前面）
        List<DateEntity> list2 = list.stream().sorted(Comparator.comparing(DateEntity::getDateA)
                .thenComparing(DateEntity::getDateB)).collect(Collectors.toList());
        // 按照 dateA 排序，如果相同，在按照 dateB 排序 （从现在到过去，现在的时间排在前面）
        List<DateEntity> list3 = list.stream().sorted(Comparator.comparing(DateEntity::getDateA)
                .thenComparing(DateEntity::getDateB).reversed()).collect(Collectors.toList());
        System.out.println("");
    }
}


@Data
class DateEntity {
    private LocalDate dateA;
    private LocalDate dateB;
}