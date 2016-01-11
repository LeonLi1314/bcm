package com.rtmap.traffic.bcm.domain;

/**
 * 一维多结果视图对象
 * 
 * @author liqingshan 2015-12-31
 *
 */
public class DimensionMultiAnalyzeDto {
	private String name;
	private String firstValue;
	private String secondValue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstValue() {
		return firstValue;
	}

	public void setFirstValue(String firstValue) {
		this.firstValue = firstValue;
	}

	public String getSecondValue() {
		return secondValue;
	}

	public void setSecondValue(String secondValue) {
		this.secondValue = secondValue;
	}
}
