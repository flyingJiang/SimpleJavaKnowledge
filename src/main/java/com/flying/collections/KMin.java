package com.flying.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class KMin {

    public static void main(String[] args) {
        final int findIndex = 3;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }
        List<Integer> reslist = list.stream().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        reslist.stream().forEach(integer -> {
            System.out.println(integer);
        });
        System.out.println("第" + findIndex + "小的数值是：" + reslist.get(findIndex-1));
    }
}
