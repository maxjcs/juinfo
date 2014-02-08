package com.hlx.juinfo.dal.biz.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据对象
 * @since 2013-01-27
 */
public class MovieDO implements Serializable {

    private static final long serialVersionUID = 135926049498341560L;

    /**
     * column movie.id
     */
    private Integer id;

    /**
     * column movie.subject  主题
     */
    private String subject;

    /**
     * column movie.year  上市时间
     */
    private String year;

    /**
     * column movie.actors  演员
     */
    private String actors;

    /**
     * column movie.director  导演
     */
    private String director;

    /**
     * column movie.detail  描述
     */
    private String detail;

    /**
     * column movie.pic_url  图片
     */
    private String picUrl;

    /**
     * column movie.star_level  星级
     */
    private Integer starLevel;

    /**
     * column movie.open_url  播放url
     */
    private String openUrl;

    /**
     * column movie.type  类型
     */
    private String type;

    /**
     * column movie.language  语言
     */
    private String language;

    /**
     * column movie.area  地区
     */
    private String area;

    /**
     * column movie.gmt_create  创建时间
     */
    private Date gmtCreate;

    /**
     * column movie.gmt_modified  修改时间
     */
    private Date gmtModified;

    /**
     * column movie.flag  数据标志，A新增 B审核通过 C审核未通过
     */
    private String flag;

    public MovieDO() {
        super();
    }

    public MovieDO(Integer id, String subject, String year, String actors, String director, String detail, String picUrl, Integer starLevel, String openUrl, String type, String language, String area, Date gmtCreate, Date gmtModified, String flag) {
        this.id = id;
        this.subject = subject;
        this.year = year;
        this.actors = actors;
        this.director = director;
        this.detail = detail;
        this.picUrl = picUrl;
        this.starLevel = starLevel;
        this.openUrl = openUrl;
        this.type = type;
        this.language = language;
        this.area = area;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.flag = flag;
    }

    /**
     * getter for Column movie.id
     */
    public Integer getId() {
        return id;
    }

    /**
     * setter for Column movie.id
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * getter for Column movie.subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * setter for Column movie.subject
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * getter for Column movie.year
     */
    public String getYear() {
        return year;
    }

    /**
     * setter for Column movie.year
     * @param year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * getter for Column movie.actors
     */
    public String getActors() {
        return actors;
    }

    /**
     * setter for Column movie.actors
     * @param actors
     */
    public void setActors(String actors) {
        this.actors = actors;
    }

    /**
     * getter for Column movie.director
     */
    public String getDirector() {
        return director;
    }

    /**
     * setter for Column movie.director
     * @param director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * getter for Column movie.detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * setter for Column movie.detail
     * @param detail
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * getter for Column movie.pic_url
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * setter for Column movie.pic_url
     * @param picUrl
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * getter for Column movie.star_level
     */
    public Integer getStarLevel() {
        return starLevel;
    }

    /**
     * setter for Column movie.star_level
     * @param starLevel
     */
    public void setStarLevel(Integer starLevel) {
        this.starLevel = starLevel;
    }

    /**
     * getter for Column movie.open_url
     */
    public String getOpenUrl() {
        return openUrl;
    }

    /**
     * setter for Column movie.open_url
     * @param openUrl
     */
    public void setOpenUrl(String openUrl) {
        this.openUrl = openUrl;
    }

    /**
     * getter for Column movie.type
     */
    public String getType() {
        return type;
    }

    /**
     * setter for Column movie.type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * getter for Column movie.language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * setter for Column movie.language
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * getter for Column movie.area
     */
    public String getArea() {
        return area;
    }

    /**
     * setter for Column movie.area
     * @param area
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * getter for Column movie.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * setter for Column movie.gmt_create
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * getter for Column movie.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * setter for Column movie.gmt_modified
     * @param gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * getter for Column movie.flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * setter for Column movie.flag
     * @param flag
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

}