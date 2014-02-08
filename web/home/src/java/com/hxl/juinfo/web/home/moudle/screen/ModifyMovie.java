/*
 * Copyright 2012 zgnet.com All right reserved. This software is the
 * confidential and proprietary information of zgnet.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with zgnet.com.
 */
package com.hxl.juinfo.web.home.moudle.screen;

import javax.annotation.Resource;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;
import com.hlx.juinfo.common.service.MovieService;
import com.hlx.juinfo.dal.biz.dal.dataobject.MovieDO;

/**
 * 类ModifyMovie.java的实现描述：TODO 类实现描述 
 * @author maxjcs 2012-9-12 上午11:06:38
 */
public class ModifyMovie {
    
    private static final Logger logger = LoggerFactory.getLogger(ModifyMovie.class);
    
    @Resource
    MovieService movieService;
    
    public void execute(@Param( name="id") Integer id, Navigator nav,Context context) {
        
        if(id ==null || id<=0){
            context.put("isSuccess", Boolean.FALSE);
            return ;
        }
        
        MovieDO   movieDO= movieService.findItemDOByPrimaryKey(id);
       
       context.put("movieDO", movieDO);
       
      // context.put("isSuccess", Boolean.TRUE);
    }

}
