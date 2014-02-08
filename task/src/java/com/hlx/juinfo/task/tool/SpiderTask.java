/*
 * Copyright 2013 zgnet.com All right reserved. This software is the
 * confidential and proprietary information of zgnet.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with zgnet.com.
 */
package com.hlx.juinfo.task.tool;

import java.io.File;

import com.hlx.juinfo.task.AbstractMain;
import com.hlx.juinfo.task.service.SpiderService;

/**
 * 类SpiderTask.java的实现描述：TODO 类实现描述 
 * @author maxjcs 2013-1-29 上午10:34:17
 */
public class SpiderTask extends AbstractMain{
    
    protected SpiderTask(){
        super(new File(getBaseDir()), "classpath/task/log4j.xml");
    }

    private static String getBaseDir() {
        String basedir = System.getProperty("project.home");

        if (basedir == null) {
            System.setProperty("project.home", System.getProperty("user.dir", ""));
        }

        basedir = System.getProperty("project.home");
        if (!basedir.endsWith(File.separator)) {
            basedir += File.separator;
        }

        return basedir;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpiderTask task = new SpiderTask();
        SpiderService spiderService = (SpiderService) task.getBean("spiderService");
        spiderService.toSpider();
        task.exit();
        System.exit(0);
    }

}
