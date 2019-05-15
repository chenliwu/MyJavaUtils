package com.chenliwu.java.utils.demo.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-14 15:26
 */
public class StreamTester {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        ////skip(n)：跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。
        String[][] data = new String[][]{{"AAA", "BBB", "CCC"}, {"DDD", "EEE", "FFF"},{"DDD","EEE","FFF"}};
        String[] result= Arrays.stream(data)
                .parallel()
                .skip(1)
                .filter(e -> e != null)
                .distinct()
                .map((e)->{
                    Arrays.stream(e).forEach(System.out::println);
                    return e.toString();
                })
                .toArray(String[]::new);

    }

}
