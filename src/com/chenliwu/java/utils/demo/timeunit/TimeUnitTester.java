package com.chenliwu.java.utils.demo.timeunit;

import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-14 11:12
 */
public class TimeUnitTester {

    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test1() {
        System.out.println("\nTimeUnit.MILLISECONDS");
        long stepTs = 60000;    //单位：毫秒
        long millis = TimeUnit.MILLISECONDS.toMillis(stepTs);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(stepTs);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(stepTs);
        System.out.println("millis=" + millis);
        System.out.println("seconds=" + seconds);
        System.out.println("minutes=" + minutes);
    }

    public static void test2(){
        System.out.println("\nTimeUnit.MINUTES");
        long stepTs = 60000;    //单位：毫秒
        long millis = TimeUnit.MINUTES.toMillis(stepTs);
        long seconds = TimeUnit.MINUTES.toSeconds(stepTs);
        long minutes = TimeUnit.MINUTES.toMinutes(stepTs);
        System.out.println("millis=" + millis);
        System.out.println("seconds=" + seconds);
        System.out.println("minutes=" + minutes);
    }

}
