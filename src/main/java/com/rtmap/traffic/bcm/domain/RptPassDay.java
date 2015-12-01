package com.rtmap.traffic.bcm.domain;

import java.util.Date;

public class RptPassDay {
    private Integer id;

    private Date statsDay;

    private String buildingNo;

    private Integer passCount;

    private Integer scanPassCount;

    private Integer photoPassCount;

    private Integer manualAddPassCount;

    private Integer scanHurriedPassCount;

    private Double scanHurriedRate;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStatsDay() {
        return statsDay;
    }

    public void setStatsDay(Date statsDay) {
        this.statsDay = statsDay;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo == null ? null : buildingNo.trim();
    }

    public Integer getPassCount() {
        return passCount;
    }

    public void setPassCount(Integer passCount) {
        this.passCount = passCount;
    }

    public Integer getScanPassCount() {
        return scanPassCount;
    }

    public void setScanPassCount(Integer scanPassCount) {
        this.scanPassCount = scanPassCount;
    }

    public Integer getPhotoPassCount() {
        return photoPassCount;
    }

    public void setPhotoPassCount(Integer photoPassCount) {
        this.photoPassCount = photoPassCount;
    }

    public Integer getManualAddPassCount() {
        return manualAddPassCount;
    }

    public void setManualAddPassCount(Integer manualAddPassCount) {
        this.manualAddPassCount = manualAddPassCount;
    }

    public Integer getScanHurriedPassCount() {
        return scanHurriedPassCount;
    }

    public void setScanHurriedPassCount(Integer scanHurriedPassCount) {
        this.scanHurriedPassCount = scanHurriedPassCount;
    }

    public Double getScanHurriedRate() {
        return scanHurriedRate;
    }

    public void setScanHurriedRate(Double scanHurriedRate) {
        this.scanHurriedRate = scanHurriedRate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}