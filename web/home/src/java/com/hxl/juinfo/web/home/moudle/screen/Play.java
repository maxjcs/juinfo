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
 * 类Play.java的实现描述：播放页面
 * @author maxjcs 2013-3-1 下午4:21:07
 */
public class Play {
    
    private static final Logger logger = LoggerFactory.getLogger(Play.class);

    @Resource
    MovieService                movieService;

    public void execute(@Param(name = "id") Integer id, Navigator nav, Context context) {

        try {
            MovieDO movie = movieService.findItemDOByPrimaryKey(id);
            context.put("movie", movie);
            
         // 获取相似视频推荐，根据类型，取前5个。
            MovieDO queryDO = new MovieDO();
            if (movie != null) {
                queryDO.setType(movie.getType());
            }
            PageList pageList=movieService.queryItems(queryDO, 1);
            context.put("pageList", pageList.subList(0,5));
        } catch (Exception ex) {
            logger.error("Play.execute() is error", ex);
        }
    }

}
