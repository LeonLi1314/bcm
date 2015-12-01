package com.rtmap.traffic.bcm.domain;

import java.util.Date;

public class RptVehicleChargeSub {
    private Integer id;

    private Date statsDay;

    private String buildingNo;

    private String vehicleNo;

    private Date lastEndChargeTime;

    private Date thisBeginChargeTime;

    private Integer tripDistance;

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

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo == null ? null : vehicleNo.trim();
    }

    public Date getLastEndChargeTime() {
        return lastEndChargeTime;
    }

    public void setLastEndChargeTime(Date lastEndChargeTime) {
        this.lastEndChargeTime = lastEndChargeTime;
    }

    public Date getThisBeginChargeTime() {
        return thisBeginChargeTime;
    }

    public void setThisBeginChargeTime(Date thisBeginChargeTime) {
        this.thisBeginChargeTime = thisBeginChargeTime;
    }

    public Integer getTripDistance() {
        return tripDistance;
    }

    public void setTripDistance(Integer tripDistance) {
        this.tripDistance = tripDistance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}