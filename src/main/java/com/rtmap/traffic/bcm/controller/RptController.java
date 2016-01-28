package com.rtmap.traffic.bcm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rtmap.traffic.bcm.domain.DriverCond;
import com.rtmap.traffic.bcm.domain.PassCond;
import com.rtmap.traffic.bcm.domain.RptDriverDay;
import com.rtmap.traffic.bcm.domain.RptDriverHour;
import com.rtmap.traffic.bcm.domain.RptDriverSubsection;
import com.rtmap.traffic.bcm.domain.RptPassDay;
import com.rtmap.traffic.bcm.domain.RptPassDistribute;
import com.rtmap.traffic.bcm.domain.RptVehicleChargeDay;
import com.rtmap.traffic.bcm.domain.RptVehicleChargeSub;
import com.rtmap.traffic.bcm.domain.RptVehicleTripHour;
import com.rtmap.traffic.bcm.domain.VehicleCond;
import com.rtmap.traffic.bcm.service.IRptService;

import lqs.frame.util.DatePatterns;
import lqs.frame.util.DateUtils;
import my.web.BaseController;
import operamasks.ui.model.DataConvertor;
import operamasks.ui.model.GridDataModel;

@Controller
@RequestMapping("rpt")
public class RptController extends BaseController {
	@Resource
	private IRptService rptService;
	@Resource
	private ParamUtils paramUtils;

	// mytest 只有12月1号有数据
	// private String preDateStr = "2015-12-01";
	// private String preDayBeginHour = "2015-12-01 00";
	// private String preDayEndHour = "2015-12-01 24";
	private String preDateStr = DateUtils.formatDate(DateUtils.addDay(DateUtils.getCurrentDate(), -1));
	private String preDayBeginHour = DateUtils.formatDate(
			DateUtils.addHour(DateUtils.addDay(DateUtils.getCurrentDate(), -1), 1), DatePatterns.POPULAR_DATE_24HOUR);
	private String preDayEndHour = DateUtils.formatDate(
			DateUtils.addHour(DateUtils.addDay(DateUtils.getCurrentDate(), -1), 23), DatePatterns.POPULAR_DATE_24HOUR);

	@RequestMapping("/driverSub")
	public String rptDriverSub(Model model) {
		model.addAttribute("preDay", preDateStr);
		return "rpt/driverSubStats";
	}

	@ResponseBody
	@RequestMapping("/getRptDriverSub.do")
	public GridDataModel<RptDriverSubsection> getRptDriverSubsectionByCond(HttpServletRequest request) {
		DriverCond cond = paramUtils.convertRptDriverCond(request);

		List<RptDriverSubsection> list = rptService.getRptDriverSubByCond(cond);

		return DataConvertor.convertToGridData(list);
	}

	@RequestMapping("/driverDay")
	public String rptDriverDay(Model model) {
		model.addAttribute("preDay", preDateStr);
		return "rpt/driverDayStats";
	}

	@ResponseBody
	@RequestMapping("/getRptDriverDay.do")
	public GridDataModel<RptDriverDay> getRptDriverDayByCond(HttpServletRequest request) {
		DriverCond cond = paramUtils.convertRptDriverCond(request);
		List<RptDriverDay> list = rptService.getRptDriverDayByCond(cond);

		return DataConvertor.convertToGridData(list);
	}

	@RequestMapping("/driverHour")
	public String rptDriverHour(Model model) {
		model.addAttribute("preDayBeginHour", preDayBeginHour);
		model.addAttribute("preDayEndHour", preDayEndHour);
		return "rpt/driverHourStats";
	}

	@ResponseBody
	@RequestMapping("/getRptDriverHour.do")
	public GridDataModel<RptDriverHour> getRptDriverHourByCond(HttpServletRequest request) {
		DriverCond cond = paramUtils.convertRptDriverCond(request, false);
		List<RptDriverHour> list = rptService.getRptDriverHourByCond(cond);

		return DataConvertor.convertToGridData(list);
	}

	@RequestMapping("/passDay")
	public String rptPassDay(Model model) {
		model.addAttribute("preDay", preDateStr);
		return "rpt/passDayStats";
	}

	@ResponseBody
	@RequestMapping("/getRptPassDay.do")
	public GridDataModel<RptPassDay> getRptPassDayByCond(HttpServletRequest request) {
		PassCond cond = paramUtils.convertRptPassCond(request);
		List<RptPassDay> list = rptService.getRptPassDayByCond(cond);

		return DataConvertor.convertToGridData(list);
	}

	@RequestMapping("/passDistribute")
	public String rptPassDistribute(Model model) {
		model.addAttribute("preDay", preDateStr);
		return "rpt/passDistributeStats";
	}

	@ResponseBody
	@RequestMapping("/getRptPassDistribute.do")
	public GridDataModel<RptPassDistribute> getRptPassDistributeByCond(HttpServletRequest request) {
		PassCond cond = paramUtils.convertRptPassCond(request);
		List<RptPassDistribute> list = rptService.getRptPassDistributeByCond(cond);

		return DataConvertor.convertToGridData(list);
	}

	@RequestMapping("/vehicleChargeDay")
	public String rptVehicleChargeDay(Model model) {
		model.addAttribute("preDay", preDateStr);
		return "rpt/vehicleChargeDayStats";
	}

	@ResponseBody
	@RequestMapping("/getRptVehicleChargeDay.do")
	public GridDataModel<RptVehicleChargeDay> getRptVehicleChargeDayByCond(HttpServletRequest request) {
		VehicleCond cond = paramUtils.convertRptVehicleCond(request);
		List<RptVehicleChargeDay> list = rptService.getRptVehicleChargeDayByCond(cond);

		return DataConvertor.convertToGridData(list);
	}

	@RequestMapping("/vehicleChargeSub")
	public String rptVehicleChargeSub(Model model) {
		model.addAttribute("preDay", preDateStr);
		return "rpt/vehicleChargeSubStats";
	}

	@ResponseBody
	@RequestMapping("/getRptVehicleChargeSub.do")
	public GridDataModel<RptVehicleChargeSub> getRptVehicleChargeSubByCond(HttpServletRequest request) {
		VehicleCond cond = paramUtils.convertRptVehicleCond(request);
		List<RptVehicleChargeSub> list = rptService.getRptVehicleChargeSubByCond(cond);

		return DataConvertor.convertToGridData(list);
	}

	@RequestMapping("/vehicleTripHour")
	public String rptVehicleTripHour(Model model) {
		model.addAttribute("preDayBeginHour", preDayBeginHour);
		model.addAttribute("preDayEndHour", preDayEndHour);
		return "rpt/vehicleTripHourStats";
	}

	@ResponseBody
	@RequestMapping("/getRptVehicleTripHour.do")
	public GridDataModel<RptVehicleTripHour> getRptVehicleTripHourByCond(HttpServletRequest request) {
		VehicleCond cond = paramUtils.convertRptVehicleCond(request, false);
		List<RptVehicleTripHour> list = rptService.getRptVehicleTripHourByCond(cond);

		return DataConvertor.convertToGridData(list);
	}
}
