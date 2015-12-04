package com.rtmap.traffic.bcm.controller;

import java.util.List;

import javax.annotation.Resource;

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

import lqs.frame.util.DateUtils;
import lqs.frame.util.StringUtils;
import my.web.BaseController;
import operamasks.ui.model.GridDataModel;

@Controller
@RequestMapping("rpt")
public class RptController extends BaseController {
	@Resource
	IRptService rptService;

	@RequestMapping("/rptDriverSub")
	public String rptDriverSub(Model model) {
		model.addAttribute("preDay", DateUtils.addDay(DateUtils.getCurrentDate(), -1));
		return "rpt/driverSubStats";
	}

	@ResponseBody
	@RequestMapping("/getRptDriverSubform.do")
	public GridDataModel<RptDriverSubsection> getRptDriverSubsectionByCond() {
		String buildingNo = param("buildingNo");
		String driverNo = param("driverNo");
		String vehicleNo = param("vehicleNo");
		String beginStatsDay = param("beginStatsDay");
		String endStatsDay = param("endStatsDay");

		RptDriverCond cond = new RptDriverCond();

		if (!StringUtils.isNullOrEmpty(buildingNo)) {
			cond.setBuildingNo(buildingNo);
		}
		if (!StringUtils.isNullOrEmpty(driverNo)) {
			cond.setDriverNo(driverNo);
		}
		if (!StringUtils.isNullOrEmpty(vehicleNo)) {
			cond.setVehicleNo(vehicleNo);
		}
		if (!StringUtils.isNullOrEmpty(beginStatsDay)) {
			cond.setBeginStatsDay(DateUtils.parseDate(beginStatsDay));
		}
		if (!StringUtils.isNullOrEmpty(endStatsDay)) {
			// 页面开始结束日期都是当天，实际查询时结束日期条件应该加1天
			cond.setEndStatsDay(DateUtils.addDay(DateUtils.parseDate(endStatsDay), 1));
		}

		List<RptDriverSubsection> list = rptService.getRptDriverSubByCond(cond);

		GridDataModel<RptDriverSubsection> gridData = new GridDataModel<RptDriverSubsection>();
		gridData.setTotal(list == null ? 0 : list.size());
		gridData.setRows(list);

		return gridData;
	}

	@ResponseBody
	@RequestMapping("/getRptDriverSub.do")
	public List<RptDriverSubsection> getRptDriverSubsectionByCond(@RequestBody RptDriverCond cond) {
		return rptService.getRptDriverSubByCond(cond);
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
