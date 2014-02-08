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
 * ��SpiderService.java��ʵ��������TODO ��ʵ������
 * 
 * @author maxjcs 2013-1-29 ����10:36:02
 */
public class SpiderService {

    private static final Logger logger = LoggerFactory.getLogger(SpiderService.class);

    @Resource
    Vcd77HtmlParserService       vcd77HtmlParserService;

    @Resource
    MovieDAO                    movieDAO;

    /**
     * ��ʼ������
     */
    public void toSpider() {
        for (int i =1; i <= 19; i++) {
            String p=(i==1?"":String.valueOf(i));
             String url = "http://www.77vcd.com/War/index"+p+".html";
            // ��ȡ����
            List<MovieDO> movieList = vcd77HtmlParserService.parse_list(url);
            // ���뵽���ݿ�
            for (MovieDO movie : movieList) {
                //����Ϊ�գ������롣
                if(StringUtil.isBlank(movie.getSubject())){
                    continue;
                }
                movie.setType(Constants.ZHAGN_ZHENG);
                // �Ȳ�ѯ�����Ƿ���������
                MovieDO queryDO = new MovieDO();
                queryDO.setSubject(movie.getSubject());
                List<MovieDO> list = movieDAO.findListByExample(queryDO);
                if (list == null || list.size() == 0) {
                    // ͼƬ�ɼ�������
                    String localPath = RemoteImageUtil.toLocalImageByUrl(movie.getPicUrl(), Constants.IMG_OUT_PATH);
                    movie.setPicUrl(localPath);
                    movieDAO.insertMovieDO(movie);
                }
            }
            try {
                // ��Ϣ2����
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
