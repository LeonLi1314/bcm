package com.rtmap.traffic.bcm.domain;

/**
 * 司机工作量视图对象
 * @author liqingshan
 *
 */
public class DriverWorkVo {
	private String driverNo;
	private String realName;
	private int passCount;
	private int workMinutes;
	private int tripDistance;

	public String getDriverNo() {
		return driverNo;
	}

	public void setDriverNo(String driverNo) {
		this.driverNo = driverNo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getPassCount() {
		return passCount;
	}

	public void setPassCount(int passCount) {
		this.passCount = passCount;
	}

	public int getWorkMinutes() {
		return workMinutes;
	}

	public void setWorkMinutes(int workMinutes) {
		this.workMinutes = workMinutes;
	}

	public int getTripDistance() {
		return tripDistance;
	}

	public void setTripDistance(int tripDistance) {
		this.tripDistance = tripDistance;
	}
}
