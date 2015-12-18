package com.rtmap.traffic.bcm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rtmap.traffic.bcm.service.IDictService;

import lqs.frame.ui.model.ComboStrItem;
import my.web.BaseController;

@Controller
@RequestMapping("base")
public class DictController extends BaseController {
	@Resource
	private IDictService dicService;
	
	@ResponseBody
	@RequestMapping( value="/building.do", method = RequestMethod.GET)
	public List<ComboStrItem> getBuildingSource() {
		return  dicService.getBuildings();
	}
	
	@ResponseBody
	@RequestMapping( value="/driver.do", method = RequestMethod.GET)
	public List<ComboStrItem> getDriverSource() {
		return dicService.getDrivers();
	}

	@ResponseBody
	@RequestMapping( value="/vehicle.do", method = RequestMethod.GET)
	public List<ComboStrItem> getVehicleSource() {
		return  dicService.getVehicles();
	}
	
	@ResponseBody
	@RequestMapping( value="/buildingWithAll.do", method = RequestMethod.GET)
	public List<ComboStrItem> getBuildingSourceWithAll() {
		return  dicService.getBuildingsWithAll();
	}
	
	@ResponseBody
	@RequestMapping( value="/driverWithAll.do", method = RequestMethod.GET)
	public List<ComboStrItem> getDriverSourceWithAll() {
		return dicService.getDriversWithAll();
	}

	@ResponseBody
	@RequestMapping( value="/vehicleWithAll.do", method = RequestMethod.GET)
	public List<ComboStrItem> getVehicleSourceWithAll() {
		return  dicService.getVehiclesWithAll();
	}
}
