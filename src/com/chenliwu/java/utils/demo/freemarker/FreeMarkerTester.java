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

        try{
            System.out.println("\nSQL："+sql);
            System.out.println("\n处理输出："+resolveFreemarkerSql(sql,params));
            System.out.println();
        }catch (Exception e){
            System.out.println("异常："+e.getMessage());
        }
    }

    /**
     * 解析Freemarker语法的SQL语句
     *
     * @param freemarkerSql     freemarker语法的SQL语句
     * @param params            参数映射对象
     * @return
     */
    public static String resolveFreemarkerSql(String freemarkerSql,Map<String,Object> params) throws TemplateException,IOException{
        String sql;

        //1、加载String模板串
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate("freemarkerSql",freemarkerSql);

        //2、创建模板对象
        Configuration cfg = new Configuration();
        cfg.setTemplateLoader(stringLoader);

        try {
            //3、获取模板对象
            Template template = cfg.getTemplate("freemarkerSql","utf-8");
            StringWriter writer = new StringWriter();
            //4、解析模板获得字符串
            template.process(params, writer);
            sql = writer.toString();
        } catch (TemplateException e) {
            throw new TemplateException("解析SQL语句失败，请检查SQL语句是否正确："+e.getMessage(),e.getEnvironment());
        }catch (IOException e) {
            throw e;
        }

        return sql;
    }


}
