package com.chenliwu.java.utils.demo.string;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-15 9:56
 */
public class StringTester {

    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        String str = "A B C D ";
        System.out.println(str);
        System.out.println(str.replaceAll(" ","")); //去除所有空格
    }

}
