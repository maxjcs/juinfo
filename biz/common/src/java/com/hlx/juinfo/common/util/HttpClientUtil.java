/*
 * Copyright 2013 zgnet.com All right reserved. This software is the
 * confidential and proprietary information of zgnet.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with zgnet.com.
 */
package com.hlx.juinfo.common.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;

/**
 * 类HttpClientUtil.java的实现描述：根据url规则，获取html内容
 * 
 * @author maxjcs 2013-1-26 下午9:06:16
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * 根据url获取html内容
     * 
     * @param url
     * @return
     */
    public static String getHtmlContent(String url) {
        String content = "";
        try {
            HttpClient httpClient = new HttpClient();
            // 设置连接超时时间为5秒
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "GBK");
            GetMethod method = new GetMethod(url);
            int statusCode = httpClient.executeMethod(method);
            if (statusCode == HttpStatus.SC_OK) {//返回成功
                content = method.getResponseBodyAsString();
            }
        } catch (Exception ex) {
            logger.error("SpiderService.getHtmlContent() is error!", ex);
        }
        return content;
    }
    
    public void getYiYiMovie(){
        
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String url = "http://list.yiyi.cc/0-1-0-0--0-1.aspx";
        String content = HttpClientUtil.getHtmlContent(url);
       System.out.println(content);
    }

}
