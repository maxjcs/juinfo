/*
 * Copyright 2012 zgnet.com All right reserved. This software is the
 * confidential and proprietary information of zgnet.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with yunqigou.com.
 */
package com.hlx.juinfo.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.common.lang.StringUtil;
import com.hlx.juinfo.common.constants.CookieConstants;

/**
 * @author maxjcs
 *
 */
public class CookieUtil {
	
	
	public static String getLoginId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String loginId = null;
        String token=null;
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (CookieConstants.COOKIE_TOKEN.equals(cookie.getName())) {
                    token = cookie.getValue();
                }
                if (CookieConstants.COOKIE_LOGIN_ID.equals(cookie.getName())) {
                    loginId = cookie.getValue();
                }
            }
          String secret= MD5Util.generatePassword(CookieConstants.COOKE_LOGIN+getIP(request));
          if(StringUtil.equals(secret, token)){
              return loginId;
          }
        }
        return null;
    }
	
	public static String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (CookieConstants.COOKIE_TOKEN.equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }
	
    public static String getUserName(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (CookieConstants.COOKIE_USERINFO.equals(cookie.getName())) {
                    String value = null;
                    try {
                        value = URLDecoder.decode(cookie.getValue(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        // nothing
                        return null;
                    }
                    return (value.split("\\|"))[0];
                }
            }
        }
        return null;
    }
    
    public static String getOrgName(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (CookieConstants.COOKIE_USERINFO.equals(cookie.getName())) {
                    String value = null;
                    try {
                        value = URLDecoder.decode(cookie.getValue(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        // nothing
                        return null;
                    }
                    return (value.split("\\|"))[1];
                }
            }
        }
        return null;
    }
    
    public static void removeUserLoginCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(CookieConstants.COOKIE_LOGIN_ID)) {
                    removeCookie(response, cookies[i]);
                } else if (cookies[i].getName().equals(CookieConstants.COOKIE_TOKEN)) {
                    removeCookie(response, cookies[i]);
                } else if (cookies[i].getName().equals(CookieConstants.COOKIE_USERINFO)) {
                    removeCookie(response, cookies[i]);
                }
            }
        }
    }
    
    public static void removeUserLoginCookie(HttpServletRequest request, HttpServletResponse response, String domain) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(CookieConstants.COOKIE_LOGIN_ID)) {
                    removeCookie(response, cookies[i], domain);
                } else if (cookies[i].getName().equals(CookieConstants.COOKIE_TOKEN)) {
                    removeCookie(response, cookies[i], domain);
                } else if (cookies[i].getName().equals(CookieConstants.COOKIE_USERINFO)) {
                    removeCookie(response, cookies[i], domain);
                }
            }
        }
    }
    
    public static void removeCookie(HttpServletResponse response, Cookie cookie) {
        cookie.setValue(null);
        cookie.setMaxAge(0);
        cookie.setPath(CookieConstants.PATH);
        cookie.setDomain(CookieConstants.DOMAIN_NAME);
        cookie.setVersion(0);
        response.addCookie(cookie);
    }
    
    public static void removeCookie(HttpServletResponse response, Cookie cookie, String domain) {
        cookie.setValue(null);
        cookie.setMaxAge(0);
        cookie.setPath(CookieConstants.PATH);
        cookie.setDomain(domain);
        cookie.setVersion(0);
        response.addCookie(cookie);
    }
    
    public static void setUserLoginCookie(HttpServletRequest request,HttpServletResponse response, String loginId) {
        setCookie(response, CookieConstants.COOKIE_LOGIN_ID, loginId, -1);

        setCookie(response, CookieConstants.COOKIE_TOKEN, MD5Util.generatePassword(CookieConstants.COOKE_LOGIN+getIP(request)), -1);

        // try {
        // setCookie(response, CookieConstants.COOKIE_USERINFO,
        // URLEncoder.encode(userLogin.getUserInfo(), "utf-8"),
        // userLogin.getCookieTimeout());
        // } catch (UnsupportedEncodingException e) {
        // throw new RuntimeException();
        // }
    }

    /**
     * @param key
     * @param value
     * @param maxAge
     */
    public static void setCookie(HttpServletResponse response, String key, String value, int maxAge) {
        Cookie cookie = new Cookie(key, value);
        if (maxAge >= 0) cookie.setMaxAge(maxAge * 60 * 60 * 24);
        else cookie.setMaxAge(-1);
        cookie.setPath(CookieConstants.PATH);
        cookie.setDomain(CookieConstants.DOMAIN_NAME);
        cookie.setVersion(0);
        response.addCookie(cookie);
    }

    /**
     * @param request
     * @return
     */
    public static String getIP(HttpServletRequest request) {
        String clientIp = null;
        String strClientIp = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(strClientIp) || StringUtils.isEqualsIgnoreCase(strClientIp, "unknown")) {
            clientIp = request.getRemoteAddr();
        } else {
            String[] strIps = StringUtils.split(strClientIp, ",");
            String strIp = null;
            for (int i = 0; i < strIps.length; i++) {
                strIp = strIps[i];
                if (!StringUtils.isEqualsIgnoreCase(strIp, "unknown")) {
                    clientIp = strIp;
                    break;
                }
            }
        }
        return clientIp;
    }

    /**
     * @param url url
     * @param enc encode
     */
    public static String urlParamterEncode(String url, String enc) {
        int index = -1;
        if (StringUtils.isBlank(url) || StringUtils.isBlank(enc) || (index = url.indexOf("?")) < 0) {
            return url;
        }
        StringBuffer requestString = new StringBuffer(url.substring(0, index + 1));
        String queryString = url.substring(index + 1);
        if (StringUtils.isBlank(queryString)) {
            return url;
        }
        String[] paramterArray = queryString.split("&");
        try {
            for (String paramter : paramterArray) {
                if (StringUtils.isNotBlank(paramter) && paramter.indexOf("=") > 0) {
                    String key = paramter.split("=")[0];
                    String value = paramter.split("=").length >= 2 ? paramter.split("=")[1] : "";
                    if (StringUtils.isEmpty(value)) {
                        requestString.append(key).append("=").append(value).append("&");
                    } else {
                        requestString.append(key).append("=").append(URLEncoder.encode(value, enc)).append("&");
                    }
                } else {
                    requestString.append(paramter).append("&");
                }
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        requestString.deleteCharAt(requestString.length() - 1);
        return requestString.toString();
    }

    public static String urlParamterDecode(String url, String enc) {
        int index = -1;
        if (StringUtils.isBlank(url) || StringUtils.isBlank(enc) || (index = url.indexOf("?")) < 0) {
            return url;
        }
        StringBuffer requestString = new StringBuffer(url.substring(0, index + 1));
        String queryString = url.substring(index + 1);
        if (StringUtils.isBlank(queryString)) {
            return url;
        }
        String[] paramterArray = queryString.split("&");
        try {
            for (String paramter : paramterArray) {
                if (StringUtils.isNotBlank(paramter) && paramter.indexOf("=") > 0) {
                    String key = paramter.split("=")[0];
                    String value = paramter.split("=").length >= 2 ? paramter.split("=")[1] : "";
                    if (StringUtils.isEmpty(value)) {
                        requestString.append(key).append("=").append(value).append("&");
                    } else {
                        requestString.append(key).append("=").append(URLDecoder.decode(value, enc)).append("&");
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        requestString.deleteCharAt(requestString.length() - 1);
        return requestString.toString();
    }
}
