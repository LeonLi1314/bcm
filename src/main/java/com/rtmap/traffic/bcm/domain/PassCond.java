package com.rtmap.traffic.bcm.domain;

import java.util.Date;

public class PassCond {
	private Date beginStatsDay;

	private Date endStatsDay;

	private String buildingNo;

	public Date getBeginStatsDay() {
		return beginStatsDay;
	}

	public void setBeginStatsDay(Date beginStatsDay) {
		this.beginStatsDay = beginStatsDay;
	}

	public Date getEndStatsDay() {
		return endStatsDay;
	}

	public void setEndStatsDay(Date endStatsDay) {
		this.endStatsDay = endStatsDay;
	}

	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo == null ? null : buildingNo.trim();
	}
}
