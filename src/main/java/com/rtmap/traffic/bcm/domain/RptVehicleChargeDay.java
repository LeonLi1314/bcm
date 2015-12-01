package com.rtmap.traffic.bcm.domain;

import java.util.Date;

public class RptVehicleChargeDay {
    private Integer id;

    private Date statsDay;

    private String buildingNo;

    private String vehicleNo;

    private Integer chargeCount;

    private Integer chargeMinutes;

    private Integer averageDistance;

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

    public Integer getChargeCount() {
        return chargeCount;
    }

    public void setChargeCount(Integer chargeCount) {
        this.chargeCount = chargeCount;
    }

    public Integer getChargeMinutes() {
        return chargeMinutes;
    }

    public void setChargeMinutes(Integer chargeMinutes) {
        this.chargeMinutes = chargeMinutes;
    }

    public Integer getAverageDistance() {
        return averageDistance;
    }

    public void setAverageDistance(Integer averageDistance) {
        this.averageDistance = averageDistance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}