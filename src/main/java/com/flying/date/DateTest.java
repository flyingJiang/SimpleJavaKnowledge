package com.flying.date;

import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        Date date1 = null;
        Date date2 = new Date();
        //System.out.println(date1.before(date2));

        if (null == date1) {
            System.out.println("1");
        }
        if (date1 == null){
            System.out.println("2");
        }


    }
}
