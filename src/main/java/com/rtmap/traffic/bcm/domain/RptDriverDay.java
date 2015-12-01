package com.rtmap.traffic.bcm.domain;

import java.util.Date;

public class RptDriverDay {
    private Integer id;

    private Date statsDay;

    private String buildingNo;

    private String driverNo;

    private String vehicleNo;

    private Date goToTime;

    private Date goOffTime;

    private Integer workMins;

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

    public String getDriverNo() {
        return driverNo;
    }

    public void setDriverNo(String driverNo) {
        this.driverNo = driverNo == null ? null : driverNo.trim();
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo == null ? null : vehicleNo.trim();
    }

    public Date getGoToTime() {
        return goToTime;
    }

    public void setGoToTime(Date goToTime) {
        this.goToTime = goToTime;
    }

    public Date getGoOffTime() {
        return goOffTime;
    }

    public void setGoOffTime(Date goOffTime) {
        this.goOffTime = goOffTime;
    }

    public Integer getWorkMins() {
        return workMins;
    }

    public void setWorkMins(Integer workMins) {
        this.workMins = workMins;
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