package com.rtmap.traffic.bcm.domain;

public class SysRoleResKey {
    private String roleCd;

    private String resCd;

    public String getRoleCd() {
        return roleCd;
    }

    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd == null ? null : roleCd.trim();
    }

    public String getResCd() {
        return resCd;
    }

    public void setResCd(String resCd) {
        this.resCd = resCd == null ? null : resCd.trim();
    }
}