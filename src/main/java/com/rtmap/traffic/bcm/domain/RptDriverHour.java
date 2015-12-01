package com.rtmap.traffic.bcm.domain;

import java.util.Date;

public class RptDriverHour {
    private Integer id;

    private Date statsHour;

    private String buildingNo;

    private String driverNo;

    private Integer effectWorkMins;

    private Integer tripDistance;

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

    public Date getStatsHour() {
        return statsHour;
    }

    public void setStatsHour(Date statsHour) {
        this.statsHour = statsHour;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo == null ? null : buildingNo.trim();
    }

    public String getDriverNo() {
        return driverNo;
    }

    public void setDriverNo(String driverNo) {
        this.driverNo = driverNo == null ? null : driverNo.trim();
    }

    public Integer getEffectWorkMins() {
        return effectWorkMins;
    }

    public void setEffectWorkMins(Integer effectWorkMins) {
        this.effectWorkMins = effectWorkMins;
    }

    public Integer getTripDistance() {
        return tripDistance;
    }

    public void setTripDistance(Integer tripDistance) {
        this.tripDistance = tripDistance;
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