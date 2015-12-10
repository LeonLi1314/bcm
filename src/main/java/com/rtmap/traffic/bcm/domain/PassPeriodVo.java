package com.rtmap.traffic.bcm.domain;

/**
 * 乘客分段视图
 * 
 * @author liqingshan
 *
 */
public class PassPeriodVo {
	private String date;
	private String hour;
	private int count;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
