package com.rtmap.traffic.bcm.domain;

public class SysRole {
    private String roleCd;

    private String roleName;

    public String getRoleCd() {
        return roleCd;
    }

    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd == null ? null : roleCd.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
}