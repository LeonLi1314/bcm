package com.rtmap.traffic.bcm.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.rtmap.traffic.bcm.domain.DriverCond;
import com.rtmap.traffic.bcm.domain.LocationCond;
import com.rtmap.traffic.bcm.domain.PassCond;
import com.rtmap.traffic.bcm.domain.VehicleCond;

import lqs.frame.ui.model.DataSourceUtils;
import lqs.frame.util.DatePatterns;
import lqs.frame.util.DateUtils;
import lqs.frame.util.StringUtils;

@Component
public class ParamUtils {
	/**
	 * 从请求对象中转化司机报表查询条件对象
	 * 
	 * @param request
	 *            客户端请求对象
	 * @return 司机报表查询条件对象
	 */
	public DriverCond convertRptDriverCond(HttpServletRequest request) {
		return convertRptDriverCond(request, true);
	}

	/**
	 * 从请求对象中转化司机报表查询条件对象
	 * 
	 * @param request
	 *            客户端请求对象
	 * @param changeDate
	 *            是否转化为日期，并且处理结束日期条件+1天
	 * @return 司机报表查询条件对象
	 */
	public DriverCond convertRptDriverCond(HttpServletRequest request, boolean changeDate) {
		String buildingNo = request.getParameter("buildingNo");
		String driverNo = request.getParameter("driverNo");
		String vehicleNo = request.getParameter("vehicleNo");
		String beginStatsDay = request.getParameter("beginStatsDay");
		String endStatsDay = request.getParameter("endStatsDay");

		DriverCond cond = new DriverCond();

		if (!StringUtils.isNullOrEmpty(buildingNo) && !buildingNo.equals(DataSourceUtils.ITEM_VALUE_ALL)) {
			cond.setBuildingNo(buildingNo);
		}
		if (!StringUtils.isNullOrEmpty(driverNo) && !driverNo.equals(DataSourceUtils.ITEM_VALUE_ALL)) {
			cond.setDriverNo(driverNo);
		}
		if (!StringUtils.isNullOrEmpty(vehicleNo) && !vehicleNo.equals(DataSourceUtils.ITEM_VALUE_ALL)) {
			cond.setVehicleNo(vehicleNo);
		}
		if (!StringUtils.isNullOrEmpty(beginStatsDay)) {
			if (changeDate) {
				cond.setBeginStatsDay(DateUtils.parseDate(beginStatsDay));
			} else {
				cond.setBeginStatsDay(DateUtils.parseDate(beginStatsDay, DatePatterns.POPULAR_DATE_24HOUR));
			}
		}
		if (!StringUtils.isNullOrEmpty(endStatsDay)) {
			if (changeDate) {
				// 页面开始结束日期都是当天，实际查询时结束日期条件应该加1天
				Date endDate = DateUtils.parseDate(endStatsDay);
				cond.setEndStatsDay(DateUtils.addDay(endDate, 1));
			} else {
				// 转化为小时+1
				Date endDate = DateUtils.parseDate(endStatsDay, DatePatterns.POPULAR_DATE_24HOUR);
				cond.setEndStatsDay(DateUtils.addHour(endDate, 1));
			}
		}

		return cond;
	}

	/**
	 * 从请求对象中转化乘客报表查询条件对象
	 * 
	 * @param request
	 *            客户端请求对象
	 * @return 乘客报表查询条件对象
	 */
	public PassCond convertRptPassCond(HttpServletRequest request) {
		String buildingNo = request.getParameter("buildingNo");
		String beginStatsDay = request.getParameter("beginStatsDay");
		String endStatsDay = request.getParameter("endStatsDay");

		PassCond cond = new PassCond();

		if (!StringUtils.isNullOrEmpty(buildingNo) && !buildingNo.equals(DataSourceUtils.ITEM_VALUE_ALL)) {
			cond.setBuildingNo(buildingNo);
		}
		if (!StringUtils.isNullOrEmpty(beginStatsDay)) {
			cond.setBeginStatsDay(DateUtils.parseDate(beginStatsDay));
		}
		if (!StringUtils.isNullOrEmpty(endStatsDay)) {
			// 页面开始结束日期都是当天，实际查询时结束日期条件应该加1天
			cond.setEndStatsDay(DateUtils.addDay(DateUtils.parseDate(endStatsDay), 1));
		}

		return cond;
	}

	/**
	 * 从请求对象中转化车辆报表查询条件对象（处理结束日期条件+1天）
	 * 
	 * @param request
	 *            客户端请求对象
	 * @return 车辆报表查询条件对象
	 */
	public VehicleCond convertRptVehicleCond(HttpServletRequest request) {
		return convertRptVehicleCond(request, true);
	}

	/**
	 * 从请求对象中转化车辆报表查询条件对象
	 * 
	 * @param request
	 *            客户端请求对象
	 * @param changeDate
	 *            是否转化为日期，并且处理结束日期条件+1天
	 * @return 车辆报表查询条件对象
	 */
	public VehicleCond convertRptVehicleCond(HttpServletRequest request, boolean changeDate) {
		String buildingNo = request.getParameter("buildingNo");
		String vehicleNo = request.getParameter("vehicleNo");
		String beginStatsDay = request.getParameter("beginStatsDay");
		String endStatsDay = request.getParameter("endStatsDay");

		VehicleCond cond = new VehicleCond();

		if (!StringUtils.isNullOrEmpty(buildingNo) && !buildingNo.equals(DataSourceUtils.ITEM_VALUE_ALL)) {
			cond.setBuildingNo(buildingNo);
		}
		if (!StringUtils.isNullOrEmpty(vehicleNo) && !vehicleNo.equals(DataSourceUtils.ITEM_VALUE_ALL)) {
			cond.setVehicleNo(vehicleNo);
		}
		if (!StringUtils.isNullOrEmpty(beginStatsDay)) {
			if (changeDate) {
				cond.setBeginStatsDay(DateUtils.parseDate(beginStatsDay));
			} else {
				cond.setBeginStatsDay(DateUtils.parseDate(beginStatsDay, DatePatterns.POPULAR_DATE_24HOUR));
			}
		}
		if (!StringUtils.isNullOrEmpty(endStatsDay)) {
			if (changeDate) {
				// 页面开始结束日期都是当天，实际查询时结束日期条件应该加1天
				Date endDate = DateUtils.parseDate(endStatsDay);
				cond.setEndStatsDay(DateUtils.addDay(endDate, 1));
			} else {
				// 转化为小时+1
				Date endDate = DateUtils.parseDate(endStatsDay, DatePatterns.POPULAR_DATE_24HOUR);
				cond.setEndStatsDay(DateUtils.addHour(endDate, 1));
			}
		}

		return cond;
	}

	public LocationCond convertLocationCond(HttpServletRequest request) {
		String buildingNo = request.getParameter("buildingNo");
		String driverNo = request.getParameter("driverNo");
		String vehicleNo = request.getParameter("vehicleNo");
		String day = request.getParameter("day");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");

		LocationCond cond = new LocationCond();

		if (!StringUtils.isNullOrEmpty(buildingNo)) {
			cond.setBuildingNo(buildingNo);
		}
		if (!StringUtils.isNullOrEmpty(driverNo)) {
			cond.setDriverNo(driverNo);
		}
		if (!StringUtils.isNullOrEmpty(vehicleNo)) {
			cond.setVehicleNo(vehicleNo);
		}
		if (!StringUtils.isNullOrEmpty(day)) {
				cond.setDay(DateUtils.parseDate(day));
		}
		if (!StringUtils.isNullOrEmpty(beginTime)) {
				cond.setBeginTime(DateUtils.parseDate(beginTime, DatePatterns.POPULAR_DATE24TIME));
		}
		if (!StringUtils.isNullOrEmpty(endTime)) {
				cond.setEndTime(DateUtils.parseDate(endTime, DatePatterns.POPULAR_DATE24TIME));
		}

		return cond;
	}
}
