/*
 * Copyright 2013 zgnet.com All right reserved. This software is the
 * confidential and proprietary information of zgnet.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with zgnet.com.
 */
package com.hlx.juinfo.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;

/**
 * 类ImageUtil.java的实现描述：采集网络图片到本地
 * 
 * @author maxjcs 2013-1-17 下午3:35:34
 */
public class RemoteImageUtil {

    private static final Logger logger = LoggerFactory.getLogger(RemoteImageUtil.class);

    /**
     * 读取网络图片到本地
     * 
     * @param imgUrl
     */
    public static String toLocalImageByUrl(String imgUrl, String outfilepath) {

        // String url="http://img1.gtimg.com/book/pics/hv1/188/179/1251/81392108.jpg";

        try {
            URL url = new URL(imgUrl);
            // 打开链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置请求方式为"GET"
            conn.setRequestMethod("GET");
            // 超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            // 通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
            // 得到图片的二进制数据，以二进制封装得到数据，具有通用性
            byte[] data = readInputStream(inStream);
            // new一个文件对象用来保存图片，默认保存当前工程根目录
            //获取后缀名
            String prefix=imgUrl.substring(imgUrl.length()-4, imgUrl.length());
            //重新设置目录
            Calendar cal=Calendar.getInstance();
            cal.setTime(new Date());
            String path=cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DAY_OF_MONTH)+"/";
            //创建目录
            File dir=new File(outfilepath+path);
            if(!dir.exists()){
                dir.mkdirs();
            }
            String realPath=path+System.currentTimeMillis()+prefix;
            String newFilePath=outfilepath+realPath;
            File imageFile = new File(newFilePath);
            // 创建输出流
            FileOutputStream outStream = new FileOutputStream(imageFile);
            // 写入数据
            outStream.write(data);
            // 关闭输出流
            outStream.close();
            return realPath;
        } catch (Exception ex) {
            logger.error("ImageService.toLocalImageByUrl() is error!", ex);
        }
        return "";
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 把outStream里的数据写入内存
        return outStream.toByteArray();
    }
    
    public  static void main(String[] aa){
        String imgUrl="http://img1.gtimg.com/book/pics/hv1/188/179/1251/81392108.jpg";
        RemoteImageUtil.toLocalImageByUrl(imgUrl, "c:/");
    }

}
