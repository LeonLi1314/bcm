package com.rtmap.traffic.bcm.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rtmap.traffic.bcm.domain.RptDriverCond;
import com.rtmap.traffic.bcm.domain.RptDriverDay;
import com.rtmap.traffic.bcm.domain.RptDriverHour;
import com.rtmap.traffic.bcm.domain.RptDriverSubsection;
import com.rtmap.traffic.bcm.domain.RptPassCond;
import com.rtmap.traffic.bcm.domain.RptPassDay;
import com.rtmap.traffic.bcm.domain.RptPassDistribute;
import com.rtmap.traffic.bcm.domain.RptVehicleChargeDay;
import com.rtmap.traffic.bcm.domain.RptVehicleChargeSub;
import com.rtmap.traffic.bcm.domain.RptVehicleCond;
import com.rtmap.traffic.bcm.domain.RptVehicleTripHour;
import com.rtmap.traffic.bcm.service.IRptService;

import lqs.frame.ui.model.DataSourceUtils;
import lqs.frame.util.DatePatterns;
import lqs.frame.util.DateUtils;
import lqs.frame.util.StringUtils;
import my.web.BaseController;
import operamasks.ui.model.GridDataModel;

@Controller
@RequestMapping("rpt")
public class RptController extends BaseController {
	@Resource
	private IRptService rptService;

	// mytest 只有12月1号有数据
	private String preDateStr = "2015-12-01";
	private String preDayBeginHour = "2015-12-01 00";
	private String preDayEndHour = "2015-12-01 24";
	// private String preDateStr =
	// DateUtils.formatDate(DateUtils.addDay(DateUtils.getCurrentDate(), -1));

	@RequestMapping("/driverSub")
	public String rptDriverSub(Model model) {
		model.addAttribute("preDay", preDateStr);
		return "rpt/driverSubStats";
	}

	@ResponseBody
	@RequestMapping("/getRptDriverSub.do")
	public GridDataModel<RptDriverSubsection> getRptDriverSubsectionByCond() {
		RptDriverCond cond = convertRptDriverCond();

		List<RptDriverSubsection> list = rptService.getRptDriverSubByCond(cond);

		return convertToGridData(list);
	}

	@RequestMapping("/driverDay")
	public String rptDriverDay(Model model) {
		model.addAttribute("preDay", preDateStr);
		return "rpt/driverDayStats";
	}

	@ResponseBody
	@RequestMapping("/getRptDriverDay.do")
	public GridDataModel<RptDriverDay> getRptDriverDayByCond() {
		RptDriverCond cond = convertRptDriverCond();
		List<RptDriverDay> list = rptService.getRptDriverDayByCond(cond);

		return convertToGridData(list);
	}

	@RequestMapping("/driverHour")
	public String rptDriverHour(Model model) {
		model.addAttribute("preDayBeginHour", preDayBeginHour);
		model.addAttribute("preDayEndHour", preDayEndHour);
		return "rpt/driverHourStats";
	}

	@ResponseBody
	@RequestMapping("/getRptDriverHour.do")
	public GridDataModel<RptDriverHour> getRptDriverHourByCond() {
		RptDriverCond cond = convertRptDriverCond(false);
		List<RptDriverHour> list = rptService.getRptDriverHourByCond(cond);

		return convertToGridData(list);
	}

	@RequestMapping("/passDay")
	public String rptPassDay(Model model) {
		model.addAttribute("preDay", preDateStr);
		return "rpt/passDayStats";
	}

	@ResponseBody
	@RequestMapping("/getRptPassDay.do")
	public GridDataModel<RptPassDay> getRptPassDayByCond() {
		RptPassCond cond = convertRptPassCond();
		List<RptPassDay> list = rptService.getRptPassDayByCond(cond);

		return convertToGridData(list);
	}

	@RequestMapping("/passDistribute")
	public String rptPassDistribute(Model model) {
		model.addAttribute("preDay", preDateStr);
		return "rpt/passDistributeStats";
	}

	@ResponseBody
	@RequestMapping("/getRptPassDistribute.do")
	public GridDataModel<RptPassDistribute> getRptPassDistributeByCond() {
		RptPassCond cond = convertRptPassCond();
		List<RptPassDistribute> list = rptService.getRptPassDistributeByCond(cond);

		return convertToGridData(list);
	}

	@RequestMapping("/vehicleChargeDay")
	public String rptVehicleChargeDay(Model model) {
		model.addAttribute("preDay", preDateStr);
		return "rpt/vehicleChargeDayStats";
	}

	@ResponseBody
	@RequestMapping("/getRptVehicleChargeDay.do")
	public GridDataModel<RptVehicleChargeDay> getRptVehicleChargeDayByCond() {
		RptVehicleCond cond = convertRptVehicleCond();
		List<RptVehicleChargeDay> list = rptService.getRptVehicleChargeDayByCond(cond);

		return convertToGridData(list);
	}

	@RequestMapping("/vehicleChargeSub")
	public String rptVehicleChargeSub(Model model) {
		model.addAttribute("preDay", preDateStr);
		return "rpt/vehicleChargeSubStats";
	}

	@ResponseBody
	@RequestMapping("/getRptVehicleChargeSub.do")
	public GridDataModel<RptVehicleChargeSub> getRptVehicleChargeSubByCond() {
		RptVehicleCond cond = convertRptVehicleCond();
		List<RptVehicleChargeSub> list = rptService.getRptVehicleChargeSubByCond(cond);

		return convertToGridData(list);
	}

	@RequestMapping("/vehicleTripHour")
	public String rptVehicleTripHour(Model model) {
		model.addAttribute("preDayBeginHour", preDayBeginHour);
		model.addAttribute("preDayEndHour", preDayEndHour);
		return "rpt/vehicleTripHourStats";
	}

	@ResponseBody
	@RequestMapping("/getRptVehicleTripHour.do")
	public GridDataModel<RptVehicleTripHour> getRptVehicleTripHourByCond() {
		RptVehicleCond cond = convertRptVehicleCond(false);
		List<RptVehicleTripHour> list = rptService.getRptVehicleTripHourByCond(cond);

		return convertToGridData(list);
	}

	/**
	 * 从请求对象中转化司机报表查询条件对象
	 * 
	 * @return 司机报表查询条件对象
	 */
	private RptDriverCond convertRptDriverCond() {
		return convertRptDriverCond(true);
	}

	/**
	 * 从请求对象中转化司机报表查询条件对象
	 * 
	 * @param changeDate
	 *            是否转化为日期，并且处理结束日期条件+1天
	 * @return 司机报表查询条件对象
	 */
	private RptDriverCond convertRptDriverCond(boolean changeDate) {
		String buildingNo = param("buildingNo");
		String driverNo = param("driverNo");
		String vehicleNo = param("vehicleNo");
		String beginStatsDay = param("beginStatsDay");
		String endStatsDay = param("endStatsDay");

		RptDriverCond cond = new RptDriverCond();

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
	 * @return 乘客报表查询条件对象
	 */
	private RptPassCond convertRptPassCond() {
		String buildingNo = param("buildingNo");
		String beginStatsDay = param("beginStatsDay");
		String endStatsDay = param("endStatsDay");

		RptPassCond cond = new RptPassCond();

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
	 * @return 车辆报表查询条件对象
	 */
	private RptVehicleCond convertRptVehicleCond() {
		return convertRptVehicleCond(true);
	}

	/**
	 * 从请求对象中转化车辆报表查询条件对象
	 * 
	 * @param changeDate
	 *            是否转化为日期，并且处理结束日期条件+1天
	 * @return 车辆报表查询条件对象
	 */
	private RptVehicleCond convertRptVehicleCond(boolean changeDate) {
		String buildingNo = param("buildingNo");
		String vehicleNo = param("vehicleNo");
		String beginStatsDay = param("beginStatsDay");
		String endStatsDay = param("endStatsDay");

		RptVehicleCond cond = new RptVehicleCond();

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

	/**
	 * 将list集合转化为omGrid控件所需的GridDataModel类型
	 * 
	 * @param list
	 *            报表数据集合
	 * @return GridDataModel类型对象
	 */
	private <T> GridDataModel<T> convertToGridData(List<T> list) {
		GridDataModel<T> gridData = new GridDataModel<T>();
		gridData.setTotal(list == null ? 0 : list.size());
		gridData.setRows(list);

		return gridData;
	}
}
