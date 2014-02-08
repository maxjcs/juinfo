/*
 * Copyright 2012 zgnet.com All right reserved. This software is the
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
 * ��ManageItem.java��ʵ��������������Ʒ 
 * @author maxjcs 2012-9-11 ����4:46:51
 */
public class ManageItem {
    
    private static final Logger logger = LoggerFactory.getLogger(ManageItem.class);
    
    @Resource
    MovieService movieService;
    
    public void execute(@Param( name="pageNum") Integer pageNum, Navigator nav,Context context) {

       PageList  itemList= movieService.queryItems(new MovieDO(),pageNum);
       
       context.put("itemList", itemList);
    }
}
