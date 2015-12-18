package com.rtmap.traffic.bcm.domain;

import java.util.Date;

public class LocationCond {
	private String tableName;

	private String buildingNo;

	private String floorNo;

	private String driverNo;

	private String vehicleNo;

	private Date day;

	private Date beginTime;

	private Date endTime;

	private String partitionNo;

	private Boolean isCrossBorder;

	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public String getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}

	public String getDriverNo() {
		return driverNo;
	}

	public void setDriverNo(String driverNo) {
		this.driverNo = driverNo;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getPartitionNo() {
		return partitionNo;
	}

	public void setPartitionNo(String partitionNo) {
		this.partitionNo = partitionNo;
	}

	public Boolean getIsCrossBorder() {
		return isCrossBorder;
	}

	public void setIsCrossBorder(Boolean isCrossBorder) {
		this.isCrossBorder = isCrossBorder;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
