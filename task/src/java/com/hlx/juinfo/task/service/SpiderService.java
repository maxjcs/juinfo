/*
 * Copyright 2013 zgnet.com All right reserved. This software is the
 * confidential and proprietary information of zgnet.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with zgnet.com.
 */
package com.hlx.juinfo.task.service;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.common.lang.StringUtil;
import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;
import com.hlx.juinfo.common.constants.Constants;
import com.hlx.juinfo.common.service.Vcd77HtmlParserService;
import com.hlx.juinfo.common.service.YiYiHtmlParserService;
import com.hlx.juinfo.common.util.RemoteImageUtil;
import com.hlx.juinfo.dal.biz.dal.dao.MovieDAO;
import com.hlx.juinfo.dal.biz.dal.dataobject.MovieDO;

/**
 * 类SpiderService.java的实现描述：TODO 类实现描述
 * 
 * @author maxjcs 2013-1-29 上午10:36:02
 */
public class SpiderService {

    private static final Logger logger = LoggerFactory.getLogger(SpiderService.class);

    @Resource
    Vcd77HtmlParserService       vcd77HtmlParserService;

    @Resource
    MovieDAO                    movieDAO;

    /**
     * 开始爬数据
     */
    public void toSpider() {
        for (int i =1; i <= 19; i++) {
            String p=(i==1?"":String.valueOf(i));
             String url = "http://www.77vcd.com/War/index"+p+".html";
            // 爬取数据
            List<MovieDO> movieList = vcd77HtmlParserService.parse_list(url);
            // 插入到数据库
            for (MovieDO movie : movieList) {
                //标题为空，不插入。
                if(StringUtil.isBlank(movie.getSubject())){
                    continue;
                }
                movie.setType(Constants.ZHAGN_ZHENG);
                // 先查询，看是否有重名的
                MovieDO queryDO = new MovieDO();
                queryDO.setSubject(movie.getSubject());
                List<MovieDO> list = movieDAO.findListByExample(queryDO);
                if (list == null || list.size() == 0) {
                    // 图片采集到本地
                    String localPath = RemoteImageUtil.toLocalImageByUrl(movie.getPicUrl(), Constants.IMG_OUT_PATH);
                    movie.setPicUrl(localPath);
                    movieDAO.insertMovieDO(movie);
                }
            }
            try {
                // 休息2分钟
                Thread.currentThread().sleep(2 * 1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("i==" + i);
        }
    }

    public void setVcd77HtmlParserService(Vcd77HtmlParserService vcd77HtmlParserService) {
        this.vcd77HtmlParserService = vcd77HtmlParserService;
    }



    public void setMovieDAO(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

}
