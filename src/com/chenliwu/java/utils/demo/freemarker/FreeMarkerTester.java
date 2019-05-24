package com.chenliwu.java.utils.demo.freemarker;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by chenlw on 2019/05/23  10:01.
 */
public class FreeMarkerTester {

    public static void main(String[] args) {
        //test2();
        test3();
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

    /**
     * 测试Freemarker语法的SQL语句
     *
     * 问题记录：
     * 1、默认值为字符串时，丢失单引号：${corpId!'默认成员单位ID'}，解析完后返回了 默认成员单位ID，我们期望的结果是返回'默认成员单位ID'。
     * 解决办法：
     * （1）字符串内增加转义字符：${corpId!'\'123456\''}， 返回 '123456'
     * （2）在表达式前后加入单引号： '${corpId!'123456'}'，返回 '123456'
     *
     */
    public static void test2(){
        //1、使用Freemarker表达式，${}
        //String sql = "select * from corp where corpId = ${corpId!'\'默认成员单位ID\''} and corpName = ${corpName!'默认成员单位名称'}";

        String sql = "select * from corp where corpId = '${corpId!'123456'}'";
        //String sql = "select * from corp where corpId = ${corpId!'\\'123456\\''}";

        //2、加入if/else指令
        //String sql = "select * from corp <#if corpId??> where corpId = ${corpId}</#if>";
//        StringBuilder sb = new StringBuilder();
//        sb.append("select * from corp ");
//        sb.append("<#if corpId?? && corpName??> ");
//        sb.append("where corpId = '${corpId}' and corpName = '${corpName}'");
//        sb.append("</#if> ");
//        String sql = sb.toString();

        //Map<String,Object> params = new HashMap<>();
        Map<String,Object> params = null;
        //params.put("corpId","成员单位ID");
        //params.put("corpName","成员单位名称");


        try{
            System.out.println("\nSQL："+sql);
            System.out.println("\n处理输出："+resolveFreemarkerSql(sql,params));
            System.out.println();
        }catch (Exception e){
            System.out.println("异常："+e.getMessage());
        }
    }

    public static void test3(){
        Scanner scanner = new Scanner(System.in);
        //System.out.println("请输入SQL：");
        ///String sql = scanner.nextLine();
        String sql = "SELECT * FROM (\n" +
                "SELECT \n" +
                "SC.NAME \"所属成员单位\",\n" +
                "USERNAME \"用户名\",\n" +
                "LOGIN_NAME \"登录名\",\n" +
                "SEX \"性别\",\n" +
                "PHONE \"手机\",\n" +
                "EMAIL \"邮箱\"\n" +
                "FROM SYS_USER SU JOIN SYS_CORP SC ON SU.CORP_ID = SC.ID\n" +
                "AND SU.CORP_ID = '${corpId}'\n" +
                ") cb_view WHERE 1=0";
        //Map<String,Object> params = new HashMap<>();
        //Map<String,Object> params = new HashMap<>();
        Map<String,Object> params =null;
        //params.put("corpId","asdadads");
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
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
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
