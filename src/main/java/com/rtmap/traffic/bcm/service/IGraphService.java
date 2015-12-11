package com.rtmap.traffic.bcm.service;

import java.util.List;

import com.rtmap.traffic.bcm.domain.DimensionAnalyzeDto;
import com.rtmap.traffic.bcm.domain.PassCond;

public interface IGraphService {
	/**
	 * 获取各司机总工作量
	 * 
	 * @param cond
	 *            查询条件
	 * @return 各司机的总工作量
	 */
	List<DimensionAnalyzeDto> getTotalDriverWork(PassCond cond);

	/**
	 * 获取每日乘客流量情况
	 * 
	 * @param cond
	 *            查询条件
	 * @return 乘客流量统计结果
	 */
	List<DimensionAnalyzeDto> getPassFlowVolume(PassCond cond);

	/**
	 * 获取乘客的分类情况
	 * 
	 * @param cond
	 *            查询条件
	 * @return 乘客分类统计结果
	 */
	List<DimensionAnalyzeDto> getPassSubtotal(PassCond cond);

	/**
	 * 获取乘客各时间段数量
	 * 
	 * @param cond
	 *            查询条件
	 * @return 各时间段乘客数量
	 */
	List<DimensionAnalyzeDto> getPassPeriodCount(PassCond cond);
}
