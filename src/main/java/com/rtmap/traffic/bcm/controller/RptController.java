package com.rtmap.traffic.bcm.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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

import my.web.BaseController;
import operamasks.ui.model.GridDataModel;

@Controller
@RequestMapping("rpt")
public class RptController extends BaseController {
	@Resource
	IRptService rptService;

	@ResponseBody
	@RequestMapping("/test.do")
	public List<RptDriverSubsection> Test() {
		RptDriverCond cond = new RptDriverCond();
		Date endDate = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		endDate = calendar.getTime();

		calendar.setTime(endDate);
		calendar.add(Calendar.DAY_OF_MONTH, -10);
		Date beginDate = calendar.getTime();

		cond.setBeginStatsDay(beginDate);
		cond.setEndStatsDay(endDate);

		return rptService.getRptDriverSubByCond(cond);
	}

	@RequestMapping("/rptDriverSub")
	public String login(HttpServletRequest request, Model model) {
		return "rpt/driverSubStats";
	}

	@ResponseBody
	@RequestMapping("/getRptDriverSub.do")
	public List<RptDriverSubsection> getRptDriverSubsectionByCond(@RequestBody RptDriverCond cond) {
		return rptService.getRptDriverSubByCond(cond);
	}

	@ResponseBody
	@RequestMapping("/getRptDriverSubform.do")
	public GridDataModel<RptDriverSubsection> getRptDriverSubsectionByCond(HttpServletRequest request, Model model) {
		String buildingNo = param("buildingNo");
		RptDriverCond cond = new RptDriverCond();
		cond.setBuildingNo(buildingNo);
		Date endDate = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		endDate = calendar.getTime();

		calendar.setTime(endDate);
		calendar.add(Calendar.DAY_OF_MONTH, -10);
		Date beginDate = calendar.getTime();

		cond.setBeginStatsDay(beginDate);
		cond.setEndStatsDay(endDate);
		List<RptDriverSubsection> list = rptService.getRptDriverSubByCond(cond);
		
		GridDataModel<RptDriverSubsection> gridData = new GridDataModel<RptDriverSubsection>();
		gridData.setTotal(list == null ? 0 : list.size());
		gridData.setRows(list);
		
		return gridData;
	}

	@ResponseBody
	@RequestMapping("/getRptDriverDay.do")
	public List<RptDriverDay> getRptDriverDayByCond(@RequestBody RptDriverCond cond) {
		return rptService.getRptDriverDayByCond(cond);
	}

	@ResponseBody
	@RequestMapping("/getRptDriverHour.do")
	public List<RptDriverHour> getRptDriverHourByCond(@RequestBody RptDriverCond cond) {
		return rptService.getRptDriverHourByCond(cond);
	}

	@ResponseBody
	@RequestMapping("/getRptPassDay.do")
	public List<RptPassDay> getRptPassDayByCond(@RequestBody RptPassCond cond) {
		return rptService.getRptPassDayByCond(cond);
	}

	@ResponseBody
	@RequestMapping("/getRptPassDistribute.do")
	public List<RptPassDistribute> getRptPassDistributeByCond(@RequestBody RptPassCond cond) {
		return rptService.getRptPassDistributeByCond(cond);
	}

	@ResponseBody
	@RequestMapping("/getRptVehicleChargeDay.do")
	public List<RptVehicleChargeDay> getRptVehicleChargeDayByCond(@RequestBody RptVehicleCond cond) {
		return rptService.getRptVehicleChargeDayByCond(cond);
	}

	@ResponseBody
	@RequestMapping("/getRptVehicleChargeSub.do")
	public List<RptVehicleChargeSub> getRptVehicleChargeSubByCond(@RequestBody RptVehicleCond cond) {
		return rptService.getRptVehicleChargeSubByCond(cond);
	}

	@ResponseBody
	@RequestMapping("/getRptVehicleTripHour.do")
	public List<RptVehicleTripHour> getRptVehicleTripHourByCond(@RequestBody RptVehicleCond cond) {
		return rptService.getRptVehicleTripHourByCond(cond);
	}
}
