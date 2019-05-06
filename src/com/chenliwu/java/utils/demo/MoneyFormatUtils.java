package com.chenliwu.java.utils.demo;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * 描述:金钱格式化工具类
 *
 * @author chenlw
 * @create 2019-03-01 15:33
 */
public class MoneyFormatUtils {

    public static void main(String[] args){

        Double d = 123456789.10;
        System.out.println("测试金钱格式化");
        System.out.println("test1 = "+test1(d));
        System.out.println("test2 = "+test2(d));
        System.out.println("test2 = "+test2(11.5));

    }


    public static String test1(Object obj){
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CHINA); //建立货币格式化引用
        return numberFormat.format(obj);
    }


    public static String test2(Object obj){
        //NumberFormat numberFormat = new DecimalFormat("#,###.00");
        //#,##0.00，解决金额小于1时金额显示格式不正确的问题；比如0.5元，#,###.00格式就会显示成.50，这显然不正确。
        NumberFormat numberFormat = new DecimalFormat("#,##0.00");
        return numberFormat.format(obj);
    }

}
