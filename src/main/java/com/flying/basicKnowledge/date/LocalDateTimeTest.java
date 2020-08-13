package com.flying.basicKnowledge.date;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTimeTest {
    /**
     * 这样写，跨天是错误的
     * 2020-07-01
     * 22:00:36.692
     * 2020-07-01T22:00:36.692
     * @param args
     */
    public static void main(String[] args) {
        Clock clock = Clock.systemDefaultZone();
        LocalDate endDate = LocalDate.now(clock);
        System.out.println(endDate);
        LocalTime endTime = LocalTime.now(clock).minusHours(10L).minusMinutes(30L).minusMinutes(0L);
        System.out.println(endTime);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);
        System.out.println(endDateTime);
        //直接使用LocalDateTime
        LocalDateTime endDateTime2 = LocalDateTime.now(clock).minusHours(10L).minusMinutes(30L).minusMinutes(0L);
        System.out.println(endDateTime2);
        LocalTime endTime2 = endDateTime2.toLocalTime();
        System.out.println(endTime2);
    }
}
