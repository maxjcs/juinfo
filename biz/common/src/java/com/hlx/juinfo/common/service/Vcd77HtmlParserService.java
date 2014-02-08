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
 * 类YiYiHtmlParserService.java的实现描述：分析html结构，抽取文本
 * 
 * @author maxjcs 2013-1-26 下午8:42:58
 */
public class Vcd77HtmlParserService {

    private static final Logger logger = LoggerFactory.getLogger(Vcd77HtmlParserService.class);
    
    /**
     * parse http://www.yiyi.cc/ 一一影视
     * 
     * @param htmlContent
     * @return
     */
    public List<MovieDO> parse_list(String url) {
        List<MovieDO> movieList = new ArrayList<MovieDO>();
        try {
            String htmlContent = HttpClientUtil.getHtmlContent(url);
            Parser parser = new Parser(htmlContent);
            // 设定过滤器，快速定位到要的内容
            NodeFilter filter1 = new TagNameFilter("li");
            NodeFilter filter2 = new HasAttributeFilter("onmouseover", "this.className='over'");
            NodeFilter filter = new AndFilter(filter1, filter2);
            NodeList nodeList = parser.extractAllNodesThatMatch(filter);
            if (nodeList != null && nodeList.size() > 0) {
                for (int i = 0; i < nodeList.size(); i++) {
                    Node node = nodeList.elementAt(i);
                    String liStr = node.toHtml();
                    Parser tmpPaser = Parser.createParser(liStr, "GBK");
                    // 获取电影详细页面的链接
                    NodeFilter filter_h3 = new TagNameFilter("h3");
                    NodeList tmpnodeList = tmpPaser.extractAllNodesThatMatch(filter_h3);
                    Node h3node = tmpnodeList.elementAt(0);
                    Node aNode = h3node.getFirstChild();
                    if (aNode instanceof TagNode) {
                        TagNode tagNode = (TagNode) aNode;
                        String detailUrl = tagNode.getAttribute("href");
                        MovieDO movieDO = parse_detail(detailUrl);
                         movieList.add(movieDO);
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("HtmlParserService.parse_yiyi_list() is error!", ex);
        }
        return movieList;
    }

    public MovieDO parse_detail(String url) {
        try {
            String htmlContent = HttpClientUtil.getHtmlContent(url);
            Parser parser = new Parser(htmlContent);
            CssSelectorNodeFilter divFilter = new CssSelectorNodeFilter("div[id='classpage18'] ul li");
            NodeList nodeList = parser.extractAllNodesThatMatch(divFilter);
            
            String movieName = getMovieName(nodeList);
            String actors = getActors(nodeList);
            String age = getAge(nodeList);
            String direct = getDirect(nodeList);
            String language = getLanguage(nodeList);
            String detail = getDetail(htmlContent);
            String picUrl = getImage(htmlContent);
            String area = getArea(nodeList);

            MovieDO movieDO = new MovieDO();
            movieDO.setActors(actors);
            movieDO.setDetail(detail);
            movieDO.setDirector(direct);
            movieDO.setSubject(movieName);
            movieDO.setLanguage(getLastString(language));
            movieDO.setYear(getLastString(age));
            movieDO.setArea(getLastString(area));
            movieDO.setPicUrl(picUrl);
            return movieDO;
        } catch (Exception ex) {
            logger.error("HtmlParserService.parse_yiyi_detail() is error!", ex);
        }
        return null;
    }

    // 获取电影名称
    private String getMovieName(NodeList nodeList) {
        try {
            Node nameNode = nodeList.elementAt(0);
            NodeList child_nodeList = nameNode.getChildren();
            Node name = child_nodeList.elementAt(2);
            String movieName = name.getFirstChild().getText();
            return movieName;
        } catch (Exception ex) {
            logger.error("HtmlParserService.getMovieName() is error!", ex);
        }
        return "";
    }

    // 获取演员名字
    private String getActors(NodeList nodeList) {
        try {
            Node nameNode = nodeList.elementAt(2);
            NodeList child_nodeList = nameNode.getChildren();
            String actor = "";
            for (int i = 1; i < child_nodeList.size(); i++) {
                Node node = child_nodeList.elementAt(i);
                Node temp = node.getFirstChild();
                if (temp != null) {
                    actor += temp.getText();
                    actor += "&nbsp;&nbsp;";
                }

            }
            return actor;
        } catch (Exception ex) {
            logger.error("HtmlParserService.getActors() is error!", ex);
        }
        return "";
    }

    // 获取语言
    private String getLanguage(NodeList nodeList) {
        try {
            Node nameNode = nodeList.elementAt(4);
            Node temp = nameNode.getFirstChild();
            return temp.getText();
        } catch (Exception ex) {
            logger.error("HtmlParserService.getLanguage() is error!", ex);
        }
        return "";
    }

    // 获取语言
    private String getDirect(NodeList nodeList ) {
        try {
            Node nameNode = nodeList.elementAt(1);
            NodeList child_nodeList = nameNode.getChildren();
            String direct = "";
            for (int i = 1; i < child_nodeList.size(); i++) {
                Node node = child_nodeList.elementAt(i);
                Node temp = node.getFirstChild();
                if (temp != null) {
                    direct += temp.getText();
                    direct += "&nbsp;&nbsp;";
                }
            }
            return direct;
        } catch (Exception ex) {
            logger.error("HtmlParserService.getLanguage() is error!", ex);
        }
        return "";
    }

    // 获取地区
    private String getArea(NodeList nodeList ) {
        try {
            Node nameNode = nodeList.elementAt(3);
            Node temp = nameNode.getFirstChild();
            return temp.getText();
        } catch (Exception ex) {
            logger.error("HtmlParserService.getLanguage() is error!", ex);
        }
        return "";
    }

    // 获取年份
    private String getAge(NodeList nodeList) {
        try {
            Node nameNode = nodeList.elementAt(5);
            Node temp = nameNode.getFirstChild();
            return temp.getText();
        } catch (Exception ex) {
            logger.error("HtmlParserService.getAge() is error!", ex);
        }
        return "";
    }

    // 获取剧情
    private String getDetail(String htmlContent) {
        try {
            Parser parser = new Parser(htmlContent);
            CssSelectorNodeFilter divFilter = new CssSelectorNodeFilter("div[id='classpageee'] dd");
            NodeList nodeList = parser.extractAllNodesThatMatch(divFilter);
            Node nameNode = nodeList.elementAt(0);
            Node temp = nameNode.getFirstChild();
            return temp.getText();
        } catch (Exception ex) {
            logger.error("HtmlParserService.getDetail() is error!", ex);
        }
        return "";
    }

    // 获取图片
    private String getImage(String htmlContent) {
        try {
            Parser parser = new Parser(htmlContent);
            CssSelectorNodeFilter divFilter = new CssSelectorNodeFilter("div[id='classpage17'] img");
            NodeList nodeList = parser.extractAllNodesThatMatch(divFilter);
            Node node = nodeList.elementAt(0);
            if (node instanceof ImageTag) {
                ImageTag imageTag = (ImageTag) node;
                String imgUrl = imageTag.getAttribute("src");
                return imgUrl;
            }
        } catch (Exception ex) {
            logger.error("HtmlParserService.getImage() is error!", ex);
        }
        return "";
    }

    public static String getLastString(String str) {
        if (StringUtil.isBlank(str)) {
            return "";
        }
        String[] strArr = str.split(" ： ");
        if (strArr.length == 2) {
            return strArr[1];
        }
        return "";
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String url = "http://www.77vcd.com/Sci-Fi/huangchongguojing/";
        Vcd77HtmlParserService service = new Vcd77HtmlParserService();
        service.parse_detail(url);
    }

}
