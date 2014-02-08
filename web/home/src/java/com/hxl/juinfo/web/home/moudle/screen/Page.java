/*
 * Copyright 2013 zgnet.com All right reserved. This software is the
 * confidential and proprietary information of zgnet.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with zgnet.com.
 */
package com.hxl.juinfo.web.home.moudle.screen;

import javax.annotation.Resource;

import com.hlx.juinfo.common.util.PageList;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;
import com.hlx.juinfo.common.service.MovieService;
import com.hlx.juinfo.dal.biz.dal.dataobject.MovieDO;

/**
 * 类Page.java的实现描述：网站首页
 * @author maxjcs 2013-3-1 下午4:02:49
 */
public class Page {
    
    private static final Logger logger = LoggerFactory.getLogger(Page.class);
    
    @Resource
    MovieService movieService;
    
    public void execute(@Param( name="pageNum") Integer pageNum,@Param( name="type") String type, Navigator nav,Context context) {

        try{
        MovieDO queryDO=new MovieDO();
        queryDO.setType(type);
        PageList  itemList= movieService.queryItems(queryDO,pageNum==null?1:pageNum);
        
        if(itemList!=null&&itemList.size()>0){
            MovieDO testDO=(MovieDO) itemList.get(0);
//            logger.error("testDO==="+testDO.getDetail());
        }
        
       context.put("itemList", itemList);
        }catch(Exception ex){
            logger.error("Index.execute() is error",ex);
        }
    }

}
