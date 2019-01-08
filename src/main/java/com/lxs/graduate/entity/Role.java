package com.lxs.graduate.entity;

public class Role {
    private Integer id;

    private String roleName;

    private String desc;

    public Role(Integer id, String roleName, String desc) {
        this.id = id;
        this.roleName = roleName;
        this.desc = desc;
    }

    public Role() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}