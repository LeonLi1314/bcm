package com.rtmap.traffic.bcm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@RequestMapping( "/building.do")
	public List<ComboStrItem> getBuildingSource() {
		return  dicService.getBuildingSource();
	}
	
	@ResponseBody
	@RequestMapping( "/driver.do")
	public List<ComboStrItem> getDriverSource() {
		return dicService.getDriverSource();
	}

	@ResponseBody
	@RequestMapping( "/vehicle.do")
	public List<ComboStrItem> getVehicleSource() {
		return  dicService.getVehicleSource();
	}
}
