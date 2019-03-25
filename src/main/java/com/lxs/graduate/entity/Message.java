package com.lxs.graduate.entity;

import java.util.Date;

public class Message {
    private Integer mId;

    private String fromUser;

    private String toUser;

    private String mTitle;

    private String content;

    private Date createTime;

    private Integer istransport;

    public Message(Integer mId, String fromUser, String toUser, String mTitle, String content, Date createTime, Integer istransport) {
        this.mId = mId;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.mTitle = mTitle;
        this.content = content;
        this.createTime = createTime;
        this.istransport = istransport;
    }

    public Message() {
        super();
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser == null ? null : fromUser.trim();
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser == null ? null : toUser.trim();
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle == null ? null : mTitle.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIstransport() {
        return istransport;
    }

    public void setIstransport(Integer istransport) {
        this.istransport = istransport;
    }
}