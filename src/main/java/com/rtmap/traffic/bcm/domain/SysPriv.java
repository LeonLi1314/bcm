package com.rtmap.traffic.bcm.domain;

public class SysPriv {
    private String privCd;

    private String privName;

    public String getPrivCd() {
        return privCd;
    }

    public void setPrivCd(String privCd) {
        this.privCd = privCd == null ? null : privCd.trim();
    }

    public String getPrivName() {
        return privName;
    }

    public void setPrivName(String privName) {
        this.privName = privName == null ? null : privName.trim();
    }
}