package com.chenliwu.java.utils.demo;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-01-08 13:53
 */
public class Base64Test {

    static String testImgPath = "D:/tempFileDir/testFile.jpg";

    public static void main(String[] args) {
        try {
            test1();
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }
    }

    public static void test1() {

        String strImg = getImageStr(testImgPath);
        System.out.println(strImg);
        generateImage(strImg, "D:/tempFileDir/saveFile.jpg");
    }

    /**
     * @param imgStr base64编码字符串
     * @param path   图片路径-具体到文件
     * @return
     * @Description: 将base64编码字符串转换为图片
     * @Author:
     * @CreateTime:
     */
    public static boolean generateImage(String imgStr, String path) {
        if (imgStr == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @return
     * @Description: 根据图片地址转换为base64编码字符串
     * @Author:
     * @CreateTime:
     */
    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

}
