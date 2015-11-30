package com.rtmap.traffic.bcm.domain;

public class SysMenu {
    private String menuNo;

    private String menuName;

    private String parentMenuNo;

    private Integer menuOrder;

    private String menuUrl;

    private String privCd;

    private Boolean isEnabled;

    public String getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo == null ? null : menuNo.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getParentMenuNo() {
        return parentMenuNo;
    }

    public void setParentMenuNo(String parentMenuNo) {
        this.parentMenuNo = parentMenuNo == null ? null : parentMenuNo.trim();
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getPrivCd() {
        return privCd;
    }

    public void setPrivCd(String privCd) {
        this.privCd = privCd == null ? null : privCd.trim();
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}