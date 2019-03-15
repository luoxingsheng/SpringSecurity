package com.lxs.graduate.entity;

public class Order {
    private Long id;

    private Integer pId;

    private Integer sellId;

    private Integer buyId;

    private Integer orderNum;

    private String orderTime;

    private String orderStatus;

    private Double orderMoney;

    private String payStatus;

    private String orderAddress;

    private String orderReceiver;

    private String orderPhone;

    public Order(Long id, Integer pId, Integer sellId, Integer buyId, Integer orderNum, String orderTime, String orderStatus, Double orderMoney, String payStatus, String orderAddress, String orderReceiver, String orderPhone) {
        this.id = id;
        this.pId = pId;
        this.sellId = sellId;
        this.buyId = buyId;
        this.orderNum = orderNum;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
        this.orderMoney = orderMoney;
        this.payStatus = payStatus;
        this.orderAddress = orderAddress;
        this.orderReceiver = orderReceiver;
        this.orderPhone = orderPhone;
    }

    public Order() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
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

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime == null ? null : orderTime.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress == null ? null : orderAddress.trim();
    }

    public String getOrderReceiver() {
        return orderReceiver;
    }

    public void setOrderReceiver(String orderReceiver) {
        this.orderReceiver = orderReceiver == null ? null : orderReceiver.trim();
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone == null ? null : orderPhone.trim();
    }
}