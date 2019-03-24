package com.lxs.graduate.entity;

import java.util.Date;

public class Evaluate {
    private Integer eId;

    private Integer pId;

    private Integer buyId;

    private String title;

    private String message;

    private Date createTime;

    public Evaluate(Integer eId, Integer pId, Integer buyId, String title, String message, Date createTime) {
        this.eId = eId;
        this.pId = pId;
        this.buyId = buyId;
        this.title = title;
        this.message = message;
        this.createTime = createTime;
    }

    public Evaluate() {
        super();
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getBuyId() {
        return buyId;
    }

    public void setBuyId(Integer buyId) {
        this.buyId = buyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}