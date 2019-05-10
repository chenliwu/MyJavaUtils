package com.chenliwu.java.utils.demo.java8.collections;

import java.util.Arrays;
import java.util.List;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-10 16:08
 */
public class ArrayListTester {

    public static void main(String[] args) {
        //testAnyMatch();
        testFilter();
    }

    /**
     * 测试anyMatch
     */
    public static void testAnyMatch() {
        List<String> list = Arrays.asList("AAA", "BBB", "CCC");
        //遍历列表，只有一个item返回true，anyMatch就返回true
        boolean result = list.stream().anyMatch(str -> "CCC".equals(str));
        System.out.println(result);
    }

    /**
     * 测试filter
     * filter()方法返回true的元素，才会保留。否则就会被过滤。
     */
    public static void testFilter(){
        List<String> list = Arrays.asList("AAA", "BBB", "CCC","DDD");
        list.stream().filter(str->"AAA".equals(str)||"BBB".equals(str)).forEach(System.out::println);
    }


}
