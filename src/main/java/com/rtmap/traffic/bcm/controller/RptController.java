package com.rtmap.traffic.bcm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rtmap.traffic.bcm.domain.RptDriverCond;
import com.rtmap.traffic.bcm.domain.RptDriverDay;
import com.rtmap.traffic.bcm.domain.RptDriverHour;
import com.rtmap.traffic.bcm.domain.RptDriverSubsection;
import com.rtmap.traffic.bcm.domain.RptPassDay;
import com.rtmap.traffic.bcm.domain.RptPassDistribute;
import com.rtmap.traffic.bcm.domain.RptVehicleChargeDay;
import com.rtmap.traffic.bcm.domain.RptVehicleChargeSub;
import com.rtmap.traffic.bcm.domain.RptVehicleTripHour;
import com.rtmap.traffic.bcm.service.IRptDriverSubsectionService;

@Controller
@RequestMapping("rpt")
public class RptController {
	@Resource
	IRptDriverSubsectionService driverSubService;

	@ResponseBody
	@RequestMapping("/getRptDriverSub.do")
	public List<RptDriverSubsection> getRptDriverSubsectionByCond(@RequestBody RptDriverCond cond) {
		return driverSubService.getRptByCond(cond);
	}

	@ResponseBody
	@RequestMapping("/getRptDriverDay.do")
	public List<RptDriverDay> getRptDriverDayByCond(@RequestBody RptDriverCond cond) {
		return null;
	}

	@ResponseBody
	@RequestMapping("/getRptDriverHour.do")
	public List<RptDriverHour> getRptDriverHourByCond(@RequestBody RptDriverCond cond) {
		return null;
	}

	@ResponseBody
	@RequestMapping("/getRptPassDay.do")
	public List<RptPassDay> getRptPassDayByCond(@RequestBody RptDriverCond cond) {
		return null;
	}

	@ResponseBody
	@RequestMapping("/getRptPassDistribute.do")
	public List<RptPassDistribute> getRptPassDistributeByCond(@RequestBody RptDriverCond cond) {
		return null;
	}

	@ResponseBody
	@RequestMapping("/getRptVehicleChargeDay.do")
	public List<RptVehicleChargeDay> getRptVehicleChargeDayByCond(@RequestBody RptDriverCond cond) {
		return null;
	}

	@ResponseBody
	@RequestMapping("/getRptVehicleChargeSub.do")
	public List<RptVehicleChargeSub> getRptVehicleChargeSubByCond(@RequestBody RptDriverCond cond) {
		return null;
	}

	@ResponseBody
	@RequestMapping("/getRptVehicleTripHour.do")
	public List<RptVehicleTripHour> getRptVehicleTripHourByCond(@RequestBody RptDriverCond cond) {
		return null;
	}
}
