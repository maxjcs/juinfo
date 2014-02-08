/*
 * Copyright 2012 zgnet.com All right reserved. This software is the
 * confidential and proprietary information of zgnet.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with zgnet.com.
 */
package com.hlx.juinfo.common.constants;

/**
 * 类CookieConstants.java的实现描述：TODO 类实现描�?
 * @author maxjcs 2012-3-28 上午9:25:04
 */
public class CookieConstants {
    public static final String DOMAIN_NAME     = "zgnet.com";//Cookie的域�?
    public static final String PATH            = "/";//Cookie的目�?
    public static final String COOKIE_NAME     = "_1_2_3_4_5_6";//cookie名称
    public static final String COOKIE_LOGIN_ID = COOKIE_NAME + "_0001";//Cookie中的用户�?
    public static final String COOKIE_TOKEN    = COOKIE_NAME + "_0002";//Cookie中的TOKEN
    public static final String COOKIE_USERINFO    =COOKIE_NAME + "_0003";//存放用户姓名|组织名称
    public static final int    COOKIE_TIMEOUT  = 365;//Cookie 超时时间 单位�?
    public static final String COOKE_LOGIN="is_login=true";
}
