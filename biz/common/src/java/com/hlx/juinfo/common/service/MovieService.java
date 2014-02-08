/*
 * Copyright 2013 zgnet.com All right reserved. This software is the
 * confidential and proprietary information of zgnet.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with zgnet.com.
 */
package com.hlx.juinfo.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.common.lang.Paginator;
import com.hlx.juinfo.common.constants.Constants;
import com.hlx.juinfo.common.util.PageList;
import com.hlx.juinfo.dal.biz.dal.dao.MovieDAO;
import com.hlx.juinfo.dal.biz.dal.dataobject.MovieDO;

/**
 * 类MovieService.java的实现描述：TODO 类实现描述
 * 
 * @author maxjcs 2013-2-12 下午10:19:26
 */
public class MovieService {

    @Resource
    MovieDAO movieDAO;

    /**
     * 插入视频
     * 
     * @param itemDO
     * @return
     */
    public Integer insert(MovieDO movieDO) {
        return movieDAO.insertMovieDO(movieDO);
    }

    /**
     * 更新视频
     * 
     * @param itemDO
     * @return
     */
    public Integer update(MovieDO movieDO) {
        return movieDAO.updateMovieDO(movieDO);
    }

    /**
     * 根据id查找
     */
    public MovieDO findItemDOByPrimaryKey(Integer id) {
        return movieDAO.findMovieDOByPrimaryKey(id);
    }

    /**
     * 根据条件查询电影
     * 
     * @param queryItemDO
     * @return
     */
    public PageList queryItems(MovieDO queryDO, Integer pageNum) {

        PageList pageList = new PageList();
        Paginator paginator = pageList.getPaginator();
        paginator.setItemsPerPage(Constants.PAGE_COUNT);
        pageList.setPaginator(paginator);
        
        Map param = new HashMap();
        param.put("movieDO", queryDO);
        param.put("startIndex",( pageNum - 1)*Constants.PAGE_COUNT);
        
        Integer count = movieDAO.countByParam(param);
        paginator.setItems(count);
        
        List<MovieDO> itemList = movieDAO.queryByParam(param);
        pageList.addAll(itemList);
        
        paginator.setPage(pageNum);
        
        return pageList;
    }

}
