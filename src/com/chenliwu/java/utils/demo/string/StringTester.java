package com.chenliwu.java.utils.demo.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-15 9:56
 */
public class StringTester {

    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        testDfsFillingSQL();
    }

    public static void test1(){
        String str = "A B C D ";
        System.out.println(str);
        System.out.println(str.replaceAll(" ","")); //去除所有空格
    }


    public static void test2(){
        String sql = "select * from student where corpId = ${corpId|1234}";
        //System.out.println("是否包含指定字符串："+sql.contains("${corpId"));
        int startIndex = sql.indexOf("${");
        int endIndex = sql.lastIndexOf("}");
        System.out.println("startIndex="+startIndex);
        System.out.println("endIndex="+endIndex);
        System.out.println("sql["+startIndex+","+endIndex+"]="+sql.substring(startIndex,endIndex+1));
    }

    static Map<String,String> fillingValueMap = new HashMap<>();
    static {
        fillingValueMap.put("corpId","成员单位id");
        fillingValueMap.put("userId","用户ID");
        fillingValueMap.put("corpName","成员单位名称");
    }

    /**
     * 递归处理SQL语句中的自定义表达式，
     * 表达式格式： ${字段名称|默认值}
     *
     * @param sql
     * @return
     */
    public static String dfsFillingSql(String sql){
        int expressionBeginIndex,expressionEndIndex;
        expressionBeginIndex = sql.indexOf("${");
        if(expressionBeginIndex == -1){
            return sql;
        }
        expressionEndIndex = sql.indexOf('}');
        if(expressionEndIndex == -1){
            return sql;
        }
        String fillingValue;

        //"select * from corp where corpId = ${corpId|1234} and corpName = ${corpName|成员单位}"
        //expression = ${corpName|成员单位}
        //valueString = corpName|成员单位
        String expression = sql.substring(expressionBeginIndex,expressionEndIndex+1);
        String valueString = sql.substring(expressionBeginIndex+2,expressionEndIndex);

        //先去除所有空格
        valueString = valueString.replaceAll(" ","");
        String [] values = valueString.split("\\|");
        if(values.length > 1){
            //有默认值
            fillingValue = values[1];
        }else{
            //没有默认值
            fillingValue = fillingValueMap.get(values[0]);
        }
        //使用解析好的值替换SQL语句中的表达式
        sql = sql.replace(expression,fillingValue);
        //System.out.println(sql);
        return dfsFillingSql(sql);
    }

    /**
     * 测试表达式解析和填充值
     */
    public static void test3(){
        String sql = "select * from corp where corpId = ${corpId|1234} and corpName = ${corpName|成员单位}";
        String valueString = " corpId|1234 ";
        //转义字符前面要加上双斜杠\\
        valueString = valueString.replaceAll(" ","");
        String[]values = valueString.split("\\|");
        //System.out.println(values.length);
        if(values.length > 1){
            for(String value:values){
                System.out.println(value);
            }
        }else{
            System.out.println("字符串中没有|字符");
        }

    }

    public static void testDfsFillingSQL(){

        //1、SQL语句没有表达式
        //String sql = "select * from corp";

        //2、SQL语句中有多个表达式，且所有表达式都有默认值
        //String sql = "select * from corp where corpId = ${corpId|单位id默认值} and corpName = ${corpName|单位名称默认值}";

        //3、SQL语句中有多个表达式，有的表达式有默认值，有的表达式没有默认值
        String sql = "select * from corp where corpId = ${corpId|单位id默认值} and corpName = ${corpName|单位名称默认值} and userId = ${userId}";

        //4、SQL语句中的表达式没有默认值
        //String sql2 = "select * from corp where corpId = ${corpId} and corpName = ${corpName}";

        System.out.println("\n未处理的SQL语句："+sql);

        String ansSql = dfsFillingSql(sql);
        System.out.println("\n表达式替换处理后的SQL语句："+ansSql);
        System.out.println();
    }


}
