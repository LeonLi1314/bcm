package com.rtmap.traffic.bcm.domain;

import java.util.Date;

public class Location {
    private Integer id;

    private String buildingNo;

    private String floorNo;

    private String driverNo;

    private String vehicleNo;

    private Integer xPoint;

    private Integer yPoint;

    private Date gatherTime;

    private Integer gatherStatus;

    private String partitionNo;

    private Boolean isCrossBorder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo == null ? null : buildingNo.trim();
    }

    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo == null ? null : floorNo.trim();
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

    public Integer getxPoint() {
        return xPoint;
    }

    public void setxPoint(Integer xPoint) {
        this.xPoint = xPoint;
    }

    public Integer getyPoint() {
        return yPoint;
    }

    public void setyPoint(Integer yPoint) {
        this.yPoint = yPoint;
    }

    public Date getGatherTime() {
        return gatherTime;
    }

    public void setGatherTime(Date gatherTime) {
        this.gatherTime = gatherTime;
    }

    public Integer getGatherStatus() {
        return gatherStatus;
    }

    public void setGatherStatus(Integer gatherStatus) {
        this.gatherStatus = gatherStatus;
    }

    public String getPartitionNo() {
        return partitionNo;
    }

    public void setPartitionNo(String partitionNo) {
        this.partitionNo = partitionNo == null ? null : partitionNo.trim();
    }

    public Boolean getIsCrossBorder() {
        return isCrossBorder;
    }

    public void setIsCrossBorder(Boolean isCrossBorder) {
        this.isCrossBorder = isCrossBorder;
    }
}