package com.rtmap.traffic.bcm.domain;

public class SysRes {
    private String resCd;

    private String resName;

    private String resExpression;

    public String getResCd() {
        return resCd;
    }

    public void setResCd(String resCd) {
        this.resCd = resCd == null ? null : resCd.trim();
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName == null ? null : resName.trim();
    }

    public String getResExpression() {
        return resExpression;
    }

    public void setResExpression(String resExpression) {
        this.resExpression = resExpression == null ? null : resExpression.trim();
    }
}