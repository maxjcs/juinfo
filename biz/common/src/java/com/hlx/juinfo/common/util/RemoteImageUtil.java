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
 * ��ImageUtil.java��ʵ���������ɼ�����ͼƬ������
 * 
 * @author maxjcs 2013-1-17 ����3:35:34
 */
public class RemoteImageUtil {

    private static final Logger logger = LoggerFactory.getLogger(RemoteImageUtil.class);

    /**
     * ��ȡ����ͼƬ������
     * 
     * @param imgUrl
     */
    public static String toLocalImageByUrl(String imgUrl, String outfilepath) {

        // String url="http://img1.gtimg.com/book/pics/hv1/188/179/1251/81392108.jpg";

        try {
            URL url = new URL(imgUrl);
            // ������
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // ��������ʽΪ"GET"
            conn.setRequestMethod("GET");
            // ��ʱ��Ӧʱ��Ϊ5��
            conn.setConnectTimeout(5 * 1000);
            // ͨ����������ȡͼƬ����
            InputStream inStream = conn.getInputStream();
            // �õ�ͼƬ�Ķ��������ݣ��Զ����Ʒ�װ�õ����ݣ�����ͨ����
            byte[] data = readInputStream(inStream);
            // newһ���ļ�������������ͼƬ��Ĭ�ϱ��浱ǰ���̸�Ŀ¼
            //��ȡ��׺��
            String prefix=imgUrl.substring(imgUrl.length()-4, imgUrl.length());
            //��������Ŀ¼
            Calendar cal=Calendar.getInstance();
            cal.setTime(new Date());
            String path=cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DAY_OF_MONTH)+"/";
            //����Ŀ¼
            File dir=new File(outfilepath+path);
            if(!dir.exists()){
                dir.mkdirs();
            }
            String realPath=path+System.currentTimeMillis()+prefix;
            String newFilePath=outfilepath+realPath;
            File imageFile = new File(newFilePath);
            // ���������
            FileOutputStream outStream = new FileOutputStream(imageFile);
            // д������
            outStream.write(data);
            // �ر������
            outStream.close();
            return realPath;
        } catch (Exception ex) {
            logger.error("ImageService.toLocalImageByUrl() is error!", ex);
        }
        return "";
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // ����һ��Buffer�ַ���
        byte[] buffer = new byte[1024];
        // ÿ�ζ�ȡ���ַ������ȣ����Ϊ-1������ȫ����ȡ���
        int len = 0;
        // ʹ��һ����������buffer������ݶ�ȡ����
        while ((len = inStream.read(buffer)) != -1) {
            // ���������buffer��д�����ݣ��м����������ĸ�λ�ÿ�ʼ����len�����ȡ�ĳ���
            outStream.write(buffer, 0, len);
        }
        // �ر�������
        inStream.close();
        // ��outStream�������д���ڴ�
        return outStream.toByteArray();
    }
    
    public  static void main(String[] aa){
        String imgUrl="http://img1.gtimg.com/book/pics/hv1/188/179/1251/81392108.jpg";
        RemoteImageUtil.toLocalImageByUrl(imgUrl, "c:/");
    }

}
