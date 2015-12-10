package com.rtmap.traffic.bcm.domain;

/**
 * 维度分析数据传输对象。name:分组名称；value分组:对应的值
 * 
 * @author liqingshan
 *
 */
public class DimensionAnalyzeDto {
	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
