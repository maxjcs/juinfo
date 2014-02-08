/*
 * Copyright 2013 zgnet.com All right reserved. This software is the
 * confidential and proprietary information of zgnet.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with zgnet.com.
 */
package com.hlx.juinfo.common.service;

import java.util.ArrayList;
import java.util.List;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;


import com.alibaba.common.lang.StringUtil;
import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;
import com.hlx.juinfo.common.constants.Constants;
import com.hlx.juinfo.common.util.HttpClientUtil;
import com.hlx.juinfo.common.util.RemoteImageUtil;
import com.hlx.juinfo.dal.biz.dal.dataobject.MovieDO;

/**
 * ��YiYiHtmlParserService.java��ʵ������������html�ṹ����ȡ�ı�
 * 
 * @author maxjcs 2013-1-26 ����8:42:58
 */
public class YiYiHtmlParserService {

    private static final Logger logger = LoggerFactory.getLogger(YiYiHtmlParserService.class);

    /**
     * parse http://www.dywg.cc/ ��Ӱ����
     * 
     * @param htmlContent
     * @return
     */
    public MovieDO parse_dywg(String htmlContent) {
        return null;
    }

    /**
     * parse http://www.yiyi.cc/ һһӰ��
     * 
     * @param htmlContent
     * @return
     */
    public List<MovieDO> parse_list(String url) {
        List<MovieDO> movieList=new ArrayList<MovieDO>();
        try {
            String htmlContent=HttpClientUtil.getHtmlContent(url);
            Parser parser = new Parser(htmlContent);
            // �趨�����������ٶ�λ��Ҫ������
            NodeFilter filter1 = new TagNameFilter("li");
            NodeFilter filter2 = new HasAttributeFilter("onmousemove", "this.className='cbg'");
            NodeFilter filter = new AndFilter(filter1, filter2);
            NodeList nodeList = parser.extractAllNodesThatMatch(filter);
            if (nodeList != null && nodeList.size() > 0) {
                for (int i = 0; i < nodeList.size(); i++) {
                    Node node = nodeList.elementAt(i);
                    String liStr = node.toHtml();
                    Parser tmpPaser = Parser.createParser(liStr, "GBK");
                    // ��ȡ��Ӱ��ϸҳ�������
                    NodeFilter filter_a = new TagNameFilter("a");
                    NodeFilter filter_ah = new HasAttributeFilter("class", "ah");
                    NodeFilter filter_inner = new AndFilter(filter_a, filter_ah);
                    NodeList tmpnodeList = tmpPaser.extractAllNodesThatMatch(filter_inner);
                    Node anode = tmpnodeList.elementAt(0);
                    String text = anode.getText();
                    if (StringUtil.isNotBlank(text)) {
                        String detailUrl = text.substring(text.indexOf("\"", 0) + 1, text.indexOf("\"", 10));
                        MovieDO movieDO=parse_yiyi_detail(detailUrl);
                        movieList.add(movieDO);
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("HtmlParserService.parse_yiyi_list() is error!", ex);
        }
        return movieList;
    }

    public MovieDO parse_yiyi_detail(String url) {
        try {
            String htmlContent=HttpClientUtil.getHtmlContent(url);
            String movieName = getMovieName(htmlContent);
            String actors= getActors(htmlContent);
            String age=getAge(htmlContent);
            String direct=getDirect(htmlContent);
            String language=getLanguage(htmlContent);
            String detail=getDetail(htmlContent);
            String picUrl=getImage(htmlContent);
            
            MovieDO movieDO=new   MovieDO();
            movieDO.setActors(actors);
            movieDO.setDetail(detail);
            movieDO.setDirector(direct);
            movieDO.setSubject(movieName);
            movieDO.setLanguage(language);
            movieDO.setYear(age);
            //ͼƬ�ɼ�������
            String localPath=RemoteImageUtil.toLocalImageByUrl(picUrl, Constants.IMG_OUT_PATH);
            movieDO.setPicUrl(localPath);
            return movieDO;
        } catch (Exception ex) {
            logger.error("HtmlParserService.parse_yiyi_detail() is error!", ex);
        }
        return null;
    }
    
    //��ȡ��Ӱ����
    private String getMovieName(String htmlContent) {
        try {
            Parser parser = new Parser(htmlContent);
            NodeFilter filter1 = new TagNameFilter("h3");
            NodeFilter filter2 = new HasAttributeFilter("class", "title");
            NodeFilter filter = new AndFilter(filter1, filter2);
            NodeList nodeList = parser.extractAllNodesThatMatch(filter);
            Node nameNode = nodeList.elementAt(0);
            NodeList child_nodeList = nameNode.getChildren();
            Node name = child_nodeList.elementAt(0);
            String movieName = name.getText();
            return movieName;
        } catch (Exception ex) {
            logger.error("HtmlParserService.getMovieName() is error!", ex);
        }
        return "";
    }

    // ��ȡ��Ա����
    private String getActors(String htmlContent) {
        try {
            Parser parser = new Parser(htmlContent);
            NodeFilter filter1 = new TagNameFilter("dl");
            NodeFilter filter2 = new HasAttributeFilter("class", "Actor");
            NodeFilter filter = new AndFilter(filter1, filter2);
            NodeList nodeList = parser.extractAllNodesThatMatch(filter);
            Node node=nodeList.elementAt(0);
            NodeList nodeList2=node.getChildren();
            Node node2=nodeList2.elementAt(1);
            NodeList nodeList3=node2.getChildren();
            Node node3=nodeList3.elementAt(0);
            return node3.getText();
        } catch (Exception ex) {
            logger.error("HtmlParserService.getActors() is error!", ex);
        }
        return "";
    }
    
    // ��ȡ����
    private String getLanguage(String htmlContent) {
        try {
            Parser parser = new Parser(htmlContent);
            NodeFilter filter1 = new TagNameFilter("dl");
            NodeFilter filter2 = new HasAttributeFilter("class", "Lang");
            NodeFilter filter = new AndFilter(filter1, filter2);
            NodeList nodeList = parser.extractAllNodesThatMatch(filter);
            Node node=nodeList.elementAt(0);
            NodeList nodeList2=node.getChildren();
            Node node2=nodeList2.elementAt(1);
            NodeList nodeList3=node2.getChildren();
            Node node3=nodeList3.elementAt(0);
            return node3.getText();
        } catch (Exception ex) {
            logger.error("HtmlParserService.getLanguage() is error!", ex);
        }
        return "";
    }
    
    // ��ȡ����
    private String getDirect(String htmlContent) {
        try {
            Parser parser = new Parser(htmlContent);
            NodeFilter filter1 = new TagNameFilter("dl");
            NodeFilter filter2 = new HasAttributeFilter("class", "Direct");
            NodeFilter filter = new AndFilter(filter1, filter2);
            NodeList nodeList = parser.extractAllNodesThatMatch(filter);
            Node node=nodeList.elementAt(0);
            NodeList nodeList2=node.getChildren();
            Node node2=nodeList2.elementAt(1);
            NodeList nodeList3=node2.getChildren();
            Node node3=nodeList3.elementAt(0);
            return node3.getText();
        } catch (Exception ex) {
            logger.error("HtmlParserService.getLanguage() is error!", ex);
        }
        return "";
    }
    
    // ��ȡ���
    private String getAge(String htmlContent) {
        try {
            Parser parser = new Parser(htmlContent);
            NodeFilter filter1 = new TagNameFilter("dl");
            NodeFilter filter2 = new HasAttributeFilter("class", "Age");
            NodeFilter filter = new AndFilter(filter1, filter2);
            NodeList nodeList = parser.extractAllNodesThatMatch(filter);
            Node node=nodeList.elementAt(0);
            NodeList nodeList2=node.getChildren();
            Node node2=nodeList2.elementAt(1);
            NodeList nodeList3=node2.getChildren();
            Node node3=nodeList3.elementAt(0);
            return node3.getText();
        } catch (Exception ex) {
            logger.error("HtmlParserService.getAge() is error!", ex);
        }
        return "";
    }
    
    // ��ȡ����
    private String getDetail(String htmlContent) {
        try {
            Parser parser = new Parser(htmlContent);
            NodeFilter filter1 = new TagNameFilter("div");
            NodeFilter filter2 = new HasAttributeFilter("class", "introduction");
            NodeFilter filter = new AndFilter(filter1, filter2);
            NodeList nodeList = parser.extractAllNodesThatMatch(filter);
            Node node=nodeList.elementAt(0);
            NodeList nodeList2=node.getChildren();
            Node node2=nodeList2.elementAt(0);
            NodeList nodeList3=node2.getChildren();
            Node node3=nodeList3.elementAt(0);
            return node3.getText();
        } catch (Exception ex) {
            logger.error("HtmlParserService.getAge() is error!", ex);
        }
        return "";
    }
    
    // ��ȡͼƬ
    private String getImage(String htmlContent) {
        try {
            Parser parser = new Parser(htmlContent);
            NodeFilter filter1 = new TagNameFilter("div");
            NodeFilter filter2 = new HasAttributeFilter("class", "vod-img");
            NodeFilter filter = new AndFilter(filter1, filter2);
            NodeList nodeList = parser.extractAllNodesThatMatch(filter);
            Node node=nodeList.elementAt(0);
            NodeList nodeList2=node.getChildren();
            Node node2=nodeList2.elementAt(0);
            NodeList nodeList3= node2.getChildren();
            Node nodeimg=nodeList3.elementAt(0);
            if(nodeimg instanceof ImageTag){
                ImageTag  imageTag = (ImageTag)nodeimg;
                String imgUrl = imageTag.getAttribute("src");
                return imgUrl;
            }
        } catch (Exception ex) {
            logger.error("HtmlParserService.getAge() is error!", ex);
        }
        return "";
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String url = "http://www.yiyi.cc/film1/beishueiyizhan/";
        String content = HttpClientUtil.getHtmlContent(url);
        YiYiHtmlParserService service = new YiYiHtmlParserService();
        service.parse_yiyi_detail(content);
    }

}
