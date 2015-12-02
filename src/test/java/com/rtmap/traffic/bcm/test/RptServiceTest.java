package com.rtmap.traffic.bcm.test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.rtmap.traffic.bcm.domain.RptDriverCond;
import com.rtmap.traffic.bcm.domain.RptDriverSubsection;
import com.rtmap.traffic.bcm.service.IRptService;


public class RptServiceTest {
	
	public static Date addDay(Date date, int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, i);
		date = calendar.getTime();
		return date;
	}
	
	@Resource
	IRptService service;

	@Test
	public void getRptDriverSubByCond() {
		RptDriverCond cond = new RptDriverCond();
		Date endDate = new Date();
		Date beginDate = addDay(endDate, -10);
		
		
		cond.setBeginStatsDay(beginDate);
		cond.setEndStatsDay(endDate);
		//service = new RptServiceImp();
		List<RptDriverSubsection> list = service.getRptDriverSubByCond(cond);
		
		System.out.println(list.size());
		
		
	}

	@Test
	public void getRptDriverDayByCond() {

	}

	@Test
	public void getRptDriverHourByCond() {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void getRptPassDayByCond() {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void getRptPassDistributeByCond() {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void getRptVehicleChargeDayByCond() {
		// TODO Auto-generated method stub

	}

	@Test
	public void getRptVehicleChargeSubByCond() {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void getRptVehicleTripHourByCond() {
		// TODO Auto-generated method stub

	}

}
