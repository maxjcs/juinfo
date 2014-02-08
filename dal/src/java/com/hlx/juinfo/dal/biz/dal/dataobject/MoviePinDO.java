package com.hlx.juinfo.dal.biz.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据对象
 * @since 2013-01-17
 */
public class MoviePinDO implements Serializable {

    private static final long serialVersionUID = 135840699726844651L;

    /**
     * column movie_pin.id
     */
    private Integer id;

    /**
     * column movie_pin.m_id  movie id
     */
    private Integer mId;

    /**
     * column movie_pin.who_pin  评论者
     */
    private String whoPin;

    /**
     * column movie_pin.detail  评价详情
     */
    private byte[] detail;

    /**
     * column movie_pin.gmt_create  创建时间
     */
    private Date gmtCreate;

    /**
     * column movie_pin.gmt_modified  修改时间
     */
    private Date gmtModified;

    public MoviePinDO() {
        super();
    }

    public MoviePinDO(Integer id, Integer mId, String whoPin, byte[] detail, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.mId = mId;
        this.whoPin = whoPin;
        this.detail = detail;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    /**
     * getter for Column movie_pin.id
     */
    public Integer getId() {
        return id;
    }

    /**
     * setter for Column movie_pin.id
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * getter for Column movie_pin.m_id
     */
    public Integer getmId() {
        return mId;
    }

    /**
     * setter for Column movie_pin.m_id
     * @param mId
     */
    public void setmId(Integer mId) {
        this.mId = mId;
    }

    /**
     * getter for Column movie_pin.who_pin
     */
    public String getWhoPin() {
        return whoPin;
    }

    /**
     * setter for Column movie_pin.who_pin
     * @param whoPin
     */
    public void setWhoPin(String whoPin) {
        this.whoPin = whoPin;
    }

    /**
     * getter for Column movie_pin.detail
     */
    public byte[] getDetail() {
        return detail;
    }

    /**
     * setter for Column movie_pin.detail
     * @param detail
     */
    public void setDetail(byte[] detail) {
        this.detail = detail;
    }

    /**
     * getter for Column movie_pin.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * setter for Column movie_pin.gmt_create
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * getter for Column movie_pin.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * setter for Column movie_pin.gmt_modified
     * @param gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

}