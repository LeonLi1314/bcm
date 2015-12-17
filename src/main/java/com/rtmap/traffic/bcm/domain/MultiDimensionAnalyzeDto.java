package com.rtmap.traffic.bcm.domain;

/**
 * 多维结果视图对象
 * 
 * @author liqingshan
 *
 */
public class MultiDimensionAnalyzeDto {
	private String firstDimension;
	private String secondDimension;
	private String value;

	public String getFirstDimension() {
		return firstDimension;
	}

	public void setFirstDimension(String firstDimension) {
		this.firstDimension = firstDimension;
	}

	public String getSecondDimension() {
		return secondDimension;
	}

	public void setSecondDimension(String secondDimension) {
		this.secondDimension = secondDimension;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
