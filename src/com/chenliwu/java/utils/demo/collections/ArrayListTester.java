package com.chenliwu.java.utils.demo.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-10 11:36
 */
public class ArrayListTester {


    public static void main(String[] args) {
        parseArrayToStringList();
    }

    /**
     * List对象转化成字符串列表
     */
    public static void parseArrayToStringList(){
        List<String> list = Arrays.asList("AAA","BBB","CCC");
        String [] stringList = list.toArray(new String[0]);
        for(int i = 0;i<stringList.length;i++){
            System.out.println(stringList[i]);
        }
    }

}
