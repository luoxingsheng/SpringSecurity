package com.lxs.graduate.entity;

import java.util.Date;

public class Order {
    private Integer id;

    private Integer pId;

    private Integer pAmount;

    private String pType;

    private Integer sellId;

    private Integer buyId;

    private Date orderTime;

    private String orderStatus;

    private Double orderMonry;

    private Integer payStatus;

    public Order(Integer id, Integer pId, Integer pAmount, String pType, Integer sellId, Integer buyId, Date orderTime, String orderStatus, Double orderMonry, Integer payStatus) {
        this.id = id;
        this.pId = pId;
        this.pAmount = pAmount;
        this.pType = pType;
        this.sellId = sellId;
        this.buyId = buyId;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
        this.orderMonry = orderMonry;
        this.payStatus = payStatus;
    }

    public Order() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getpAmount() {
        return pAmount;
    }

    public void setpAmount(Integer pAmount) {
        this.pAmount = pAmount;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType == null ? null : pType.trim();
    }

    public Integer getSellId() {
        return sellId;
    }

    public void setSellId(Integer sellId) {
        this.sellId = sellId;
    }

    public Integer getBuyId() {
        return buyId;
    }

    public void setBuyId(Integer buyId) {
        this.buyId = buyId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public Double getOrderMonry() {
        return orderMonry;
    }

    public void setOrderMonry(Double orderMonry) {
        this.orderMonry = orderMonry;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }
}