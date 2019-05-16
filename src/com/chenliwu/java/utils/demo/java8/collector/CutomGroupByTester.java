package com.chenliwu.java.utils.demo.java8.collector;

import com.chenliwu.java.utils.demo.java8.collector.result.ColumnIndex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-16 10:13
 */
public class CutomGroupByTester {

    public static void main(String[] args) {
        test1();
    }

    /**
     * 测试自定义收集器
     */
    public static void test1() {
        String[][] data = new String[][]{
                {"111", "222", "333"},
                {"200", "210", "220"}, {"200", "210", "220"},
                {"300", "310", "320"}, {"300", "310", "320"},
                {"400", "410", "420"}
        };
        List<ColumnIndex> dimensionList = new ArrayList<>();
        dimensionList.add(new ColumnIndex(0, null, "班级编号"));
        dimensionList.add(new ColumnIndex(1, null, "学号"));
        dimensionList.add(new ColumnIndex(2, null, "姓名"));

        ///列下标，聚合类型，列名称
        List<ColumnIndex> valuesList = new ArrayList<>();
        //valuesList.add(new ColumnIndex("avg", "班级编号"));
        valuesList.add(new ColumnIndex(0, "sum", "班级编号"));
        valuesList.add(new ColumnIndex(1, "sum", "学号"));
        valuesList.add(new ColumnIndex(2, "sum", "姓名"));

        Map<Dimensions, Double[]> grouped = Arrays.stream(data)
                .skip(1)
                .collect(Collectors.groupingBy(row -> {
                    String[] ds = dimensionList.stream()
                            .map(d -> row[d.getIndex()])
                            .toArray(String[]::new);
                    return new Dimensions(ds);
                }, AggregateCollector.getCollector(valuesList)));


        // 第三种：推荐，尤其是容量大时
        System.out.println("");
        for (Map.Entry<Dimensions, Double[]> entry : grouped.entrySet()) {
            //Map.entry<Integer,String> 映射项（键-值对）  有几个方法：用上面的名字entry
            //entry.getKey() ;entry.getValue(); entry.setValue();
            //map.entrySet()  返回此映射中包含的映射关系的 Set视图。
            System.out.println("key= " + entry.getKey());
            Arrays.stream(entry.getValue()).forEach(row -> {
                System.out.println(row);
            });
            System.out.println();
        }


    }

}
