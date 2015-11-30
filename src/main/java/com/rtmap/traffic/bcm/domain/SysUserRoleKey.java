package com.rtmap.traffic.bcm.domain;

public class SysUserRoleKey {
    private String userCd;

    private String roleCd;

    public String getUserCd() {
        return userCd;
    }

    public void setUserCd(String userCd) {
        this.userCd = userCd == null ? null : userCd.trim();
    }

    public String getRoleCd() {
        return roleCd;
    }

    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd == null ? null : roleCd.trim();
    }
}