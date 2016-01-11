package com.rtmap.traffic.bcm.domain;

public class VirtualArea {
    private String areaCode;

    private String buildingNo;

    private String floorNo;

    private Integer tlXPoint;

    private Integer tlYPoint;

    private Integer brXPoint;

    private Integer brYPoint;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
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

    public Integer getTlXPoint() {
        return tlXPoint;
    }

    public void setTlXPoint(Integer tlXPoint) {
        this.tlXPoint = tlXPoint;
    }

    public Integer getTlYPoint() {
        return tlYPoint;
    }

    public void setTlYPoint(Integer tlYPoint) {
        this.tlYPoint = tlYPoint;
    }

    public Integer getBrXPoint() {
        return brXPoint;
    }

    public void setBrXPoint(Integer brXPoint) {
        this.brXPoint = brXPoint;
    }

    public Integer getBrYPoint() {
        return brYPoint;
    }

    public void setBrYPoint(Integer brYPoint) {
        this.brYPoint = brYPoint;
    }
}