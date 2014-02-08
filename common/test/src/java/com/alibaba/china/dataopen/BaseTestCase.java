package com.alibaba.china.dataopen;

/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */

import java.net.URL;

import org.jtester.annotations.SpringApplicationContext;
import org.jtester.testng.JTester;

import com.alibaba.common.logging.spi.log4j.DOMConfigurator;

/**
 * 类BaseTestCase.java的实现描述：TODO 类实现描述
 * 
 * @author floyd 2011-3-8 下午08:30:28
 */
@SpringApplicationContext({ "springtest/datasource.xml", "springtest/biz-dal.xml" })
public class BaseTestCase extends JTester {

    static {
        initLog();
    }

    /**
     * 初始化log4j
     */
    private static void initLog() {
        URL url = BaseTestCase.class.getClassLoader().getResource("log4j_test.xml");
        if (url != null) {
            DOMConfigurator.configure(url);
        } else {
            System.err.println("not found log4jTest.xml in classpath");
        }
    }

}
