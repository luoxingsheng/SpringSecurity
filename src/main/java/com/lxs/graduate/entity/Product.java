package com.lxs.graduate.entity;

public class Product {
    private Integer id;

    private String pName;

    private String pType;

    private Double pPrice;

    private String pImg;

    private String pDesc;

    private String pStatus;

    private Integer pStock;

    private Integer userId;

    public Product(Integer id, String pName, String pType, Double pPrice, String pImg, String pDesc, String pStatus, Integer pStock, Integer userId) {
        this.id = id;
        this.pName = pName;
        this.pType = pType;
        this.pPrice = pPrice;
        this.pImg = pImg;
        this.pDesc = pDesc;
        this.pStatus = pStatus;
        this.pStock = pStock;
        this.userId = userId;
    }

    public Product() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType == null ? null : pType.trim();
    }

    public Double getpPrice() {
        return pPrice;
    }

    public void setpPrice(Double pPrice) {
        this.pPrice = pPrice;
    }

    public String getpImg() {
        return pImg;
    }

    public void setpImg(String pImg) {
        this.pImg = pImg == null ? null : pImg.trim();
    }

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc == null ? null : pDesc.trim();
    }

    public String getpStatus() {
        return pStatus;
    }

    public void setpStatus(String pStatus) {
        this.pStatus = pStatus;
    }

    public Integer getpStock() {
        return pStock;
    }

    public void setpStock(Integer pStock) {
        this.pStock = pStock;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}