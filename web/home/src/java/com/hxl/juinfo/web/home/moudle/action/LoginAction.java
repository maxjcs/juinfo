/*
 * Copyright 2012 zgnet.com All right reserved. This software is the
 * confidential and proprietary information of zgnet.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with zgnet.com.
 */
package com.hxl.juinfo.web.home.moudle.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;
import com.hlx.juinfo.common.constants.Constants;
import com.hlx.juinfo.common.constants.CookieConstants;
import com.hlx.juinfo.common.constants.WebConstants;
import com.hlx.juinfo.common.util.CookieUtil;

/**
 * ��LoginAction.java��ʵ����������¼��action 
 * @author maxjcs 2012-9-2 ����2:44:41
 */
public class LoginAction {
    
    private Logger           logger        = LoggerFactory.getLogger(LoginAction.class);

    @Resource
    private HttpServletRequest  request;
    @Resource
    private HttpServletResponse response;

    public void doLogin(@Param(name = "username") String username, @Param(name = "password") String password,
                        Navigator nav, Context context, TurbineRunData rundata) {

        // ��¼�ɹ���ת��searchҳ��
        if (StringUtils.equals(username, Constants.LOGIN_USER_NAME)
            && StringUtils.equals(password, Constants.LOGIN_PASSWORD)) {
            CookieUtil.setUserLoginCookie(request, response, username);
            // ���ص���ص�ҳ��
            nav.redirectTo(WebConstants.INDEX_LINK);
        } else {
            context.put("errorMsg", "�û��������������");
        }

        return;
    }
 
    /**
     * �˳���¼
     */
    public void doLogout(Navigator nav) {
        // String token = CookieUtil.getToken(request);
        CookieUtil.removeUserLoginCookie(request, response, CookieConstants.COOKIE_TOKEN);
        CookieUtil.removeUserLoginCookie(request, response, CookieConstants.COOKIE_LOGIN_ID);
        CookieUtil.removeUserLoginCookie(request, response, CookieConstants.DOMAIN_NAME);
        try {
            nav.redirectTo(WebConstants.USER_LOGIN_LINK);
        } catch (Exception e) {
//            logger.error(e);
//            RedirectParameters rp = nav.redirectTo(WebConstants.MESSAGE_LINK);
//            rp.withParameter(WebConstants.MESSAGE_STATUS, WebConstants.ERROR_MESSAGE_CONTENT);
            return;
        }
    }
    }


