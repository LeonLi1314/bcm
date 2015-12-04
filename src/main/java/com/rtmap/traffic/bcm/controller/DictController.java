package com.rtmap.traffic.bcm.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import my.web.BaseController;
import operamasks.ui.model.OmComboItem;

@Controller
@RequestMapping("base")
public class DictController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ResponseBody
	@RequestMapping( "/source.do")
	public List<OmComboItem> getOmComboSource() {
		List<OmComboItem> list = new ArrayList<>();
		OmComboItem item1 = new OmComboItem();
		item1.setText("测试");
		item1.setValue("1");
		list.add(item1);
		OmComboItem item10 = new OmComboItem();
		item10.setText("测试10");
		item10.setValue("10");
		list.add(item10);
		OmComboItem item1010 = new OmComboItem();
		item1010.setText("测试1010");
		item1010.setValue("1010");
		list.add(item1010);

		OmComboItem item2 = new OmComboItem();
		item2.setText("测试2");
		item2.setValue("2");
		list.add(item2);
		
		return list;
	}
	
	@ResponseBody
	@RequestMapping( "/building.do")
	public List<OmComboItem> getBuildingSource() {
		List<OmComboItem> list = new ArrayList<>();

		OmComboItem item = new OmComboItem();
		item.setText("");
		item.setValue("");
		list.add(item);
		item = new OmComboItem();
		item.setText("T3C");
		item.setValue("T3C");
		list.add(item);
		item = new OmComboItem();
		item.setText("T3E");
		item.setValue("T3E");
		list.add(item);
		
		return list;
	}
	
	@ResponseBody
	@RequestMapping( "/driver.do")
	public List<OmComboItem> getDriverSource() {
		List<OmComboItem> list = new ArrayList<>();

		OmComboItem item = new OmComboItem();
		item.setText("");
		item.setValue("");
		list.add(item);
		item = new OmComboItem();
		item.setText("杨志军");
		item.setValue("A01");
		list.add(item);
		item = new OmComboItem();
		item.setText("李宝");
		item.setValue("A02");
		list.add(item);
		
		return list;
	}

	@ResponseBody
	@RequestMapping( "/vehicle.do")
	public List<OmComboItem> getVehicleSource() {
		List<OmComboItem> list = new ArrayList<>();

		OmComboItem item = new OmComboItem();
		item.setText("");
		item.setValue("");
		list.add(item);
		item = new OmComboItem();
		item.setText("19号车");
		item.setValue("19");
		list.add(item);
		item = new OmComboItem();
		item.setText("20号车");
		item.setValue("20");
		list.add(item);
		item = new OmComboItem();
		item.setText("26号车");
		item.setValue("26");
		list.add(item);
		item = new OmComboItem();
		item.setText("38号车");
		item.setValue("38");
		list.add(item);
		item = new OmComboItem();
		item.setText("39号车");
		item.setValue("39");
		list.add(item);
		item = new OmComboItem();
		item.setText("43号车");
		item.setValue("43");
		list.add(item);
		
		return list;
	}
}
