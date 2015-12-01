package com.rtmap.traffic.bcm.domain;

import java.util.Date;

public class RptVehicleTripHour {
    private Integer id;

    private Date statsHour;

    private String buildingNo;

    private String vehicleNo;

    private Integer tripDistance;

    private Integer theoryTripDistance;

    private Double saturation;

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

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo == null ? null : vehicleNo.trim();
    }

    public Integer getTripDistance() {
        return tripDistance;
    }

    public void setTripDistance(Integer tripDistance) {
        this.tripDistance = tripDistance;
    }

    public Integer getTheoryTripDistance() {
        return theoryTripDistance;
    }

    public void setTheoryTripDistance(Integer theoryTripDistance) {
        this.theoryTripDistance = theoryTripDistance;
    }

    public Double getSaturation() {
        return saturation;
    }

    public void setSaturation(Double saturation) {
        this.saturation = saturation;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}