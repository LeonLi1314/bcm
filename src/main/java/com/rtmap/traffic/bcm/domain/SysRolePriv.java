package com.rtmap.traffic.bcm.domain;

public class SysRolePriv {
    private String roleCd;

    private String privCd;

    public String getRoleCd() {
        return roleCd;
    }

    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd == null ? null : roleCd.trim();
    }

    public String getPrivCd() {
        return privCd;
    }

    public void setPrivCd(String privCd) {
        this.privCd = privCd == null ? null : privCd.trim();
    }
}