package com.rtmap.traffic.bcm.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class RptPassDistribute {
    private Integer id;

    private Date statsDay;

    private String buildingNo;

    private Date takeTime;

    private Integer xPoint;

    private Integer yPoint;

    private String fltNo;

    private Date estimatedDepatureTime;

    private String gateNo;

    private Integer xGatePoint;

    private Integer yGatePoint;

    private Integer takeDistance;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	@JSONField(format = "yyyy-MM-dd")
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

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Date takeTime) {
        this.takeTime = takeTime;
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

    public String getFltNo() {
        return fltNo;
    }

    public void setFltNo(String fltNo) {
        this.fltNo = fltNo == null ? null : fltNo.trim();
    }

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getEstimatedDepatureTime() {
        return estimatedDepatureTime;
    }

    public void setEstimatedDepatureTime(Date estimatedDepatureTime) {
        this.estimatedDepatureTime = estimatedDepatureTime;
    }

    public String getGateNo() {
        return gateNo;
    }

    public void setGateNo(String gateNo) {
        this.gateNo = gateNo == null ? null : gateNo.trim();
    }

    public Integer getxGatePoint() {
        return xGatePoint;
    }

    public void setxGatePoint(Integer xGatePoint) {
        this.xGatePoint = xGatePoint;
    }

    public Integer getyGatePoint() {
        return yGatePoint;
    }

    public void setyGatePoint(Integer yGatePoint) {
        this.yGatePoint = yGatePoint;
    }

    public Integer getTakeDistance() {
        return takeDistance;
    }

    public void setTakeDistance(Integer takeDistance) {
        this.takeDistance = takeDistance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    private int inArea;

	public int getInArea() {
		return inArea;
	}

	public void setInArea(int inArea) {
		this.inArea = inArea;
	}
}