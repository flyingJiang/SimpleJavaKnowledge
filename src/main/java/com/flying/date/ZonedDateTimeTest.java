package com.flying.date;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class ZonedDateTimeTest {
    /**
     * 2020-07-01T09:04:58.188+08:00[Asia/Shanghai]
     * 2020-06-30T22:34:58.188+08:00[Asia/Shanghai]
     * @param args
     */
    public static void main(String[] args) {
        Date date = new Date();

        ZonedDateTime oldEffectiveOn = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        System.out.println(oldEffectiveOn);
//        ZonedDateTime newEffectiveOn = oldEffectiveOn.minusDays(1L);
        ZonedDateTime newEffectiveOn = oldEffectiveOn.minusHours(10L).minusMinutes(30L);
        System.out.println(newEffectiveOn);
        System.out.println(newEffectiveOn.isBefore(oldEffectiveOn));

    }
}
