package com.chenliwu.java.utils.demo.timeunit;

import java.time.Duration;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-14 11:19
 */
public class DurationTester {

    public static void main(String[] args) {
        test1();
    }

    /**
     *
     */
    public static void test1() {
        //Duration类，属于JAVA8特性
        //Duration.ofMillis(long millis)：获得表示毫秒数的持续时间。
        //toMinutes()：获取此持续时间内的分钟数。
        //toMillis()：将此持续时间转换为总长度(以毫秒为单位)。
        long stepTs = 60000;    //单位：毫秒
        long minutesOfDuration = Duration.ofMillis(stepTs).toMinutes();
        long secondsOfDuration = Duration.ofMillis(stepTs).toMillis() / 1000;
        System.out.println("minutesOfDuration=" + minutesOfDuration);
        System.out.println("secondsOfDuration=" + secondsOfDuration);
    }
}
