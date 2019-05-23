package com.chenliwu.java.utils.demo.freemarker;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenlw on 2019/05/23  10:01.
 */
public class FreeMarkerTester {

    public static void main(String[] args) {
        test2();
    }

    public static void test1(){
        //创建Freemarker配置实例
        Configuration cfg = new Configuration();
        try {
            cfg.setDirectoryForTemplateLoading(new File("templates"));
            //创建数据模型
            Map root = new HashMap();
            root.put("user", "老高");

            //加载模板文件
            Template t1 = cfg.getTemplate("a.ftl");
            //显示生成的数据,//将合并后的数据打印到控制台

            Writer out = new OutputStreamWriter(System.out);
            t1.process(root, out);
            out.flush();
            //显示生成的数据,//将合并后的数据直接返回成字符串！
//     StringWriter out = new StringWriter();
//     t1.process(root, out);
//     out.flush();
//     String temp = out.toString();
//     System.out.println(temp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void test2(){
        String sql = "select * from corp where corpId = ${corpId} and corpName = ${corpName!'默认成员单位名称'}";
        Map<String,Object> params = new HashMap<>();
        params.put("corpId","成员单位ID");

        System.out.println("\nSQL："+sql);
        System.out.println("\n处理输出："+resolveFreemarkerSql(sql,params));
        System.out.println();
    }

    /**
     * 解析Freemarker语法的SQL语句
     *
     * @param freemarkerSql
     * @param params
     * @return
     */
    public static String resolveFreemarkerSql(String freemarkerSql,Map<String,Object> params){
        String sql = freemarkerSql;
        Configuration cfg = new Configuration();
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate("myTemplate",freemarkerSql);

        cfg.setTemplateLoader(stringLoader);

        try {
            Template template = cfg.getTemplate("myTemplate","utf-8");
            StringWriter writer = new StringWriter();
            template.process(params, writer);
            sql = writer.toString();
        } catch (TemplateException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return sql;
    }


}
