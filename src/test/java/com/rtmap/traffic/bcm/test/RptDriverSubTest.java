package com.rtmap.traffic.bcm.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.rtmap.traffic.bcm.dao.IRptDriverDayDao;
import com.rtmap.traffic.bcm.dao.IRptDriverSubsectionDao;
import com.rtmap.traffic.bcm.domain.RptDriverDay;
import com.rtmap.traffic.bcm.domain.RptDriverSubsection;

public class RptDriverSubTest {

	@Resource
	IRptDriverSubsectionDao dao;
	@Test
	public void test() {
		RptDriverSubsection sub = dao.selectByPrimaryKey(1);
		
		sub.getDriverNo();
	}

	@Resource
	IRptDriverDayDao dayDao;
	@Test
	public void test2() {
		RptDriverDay day = dayDao.selectByPrimaryKey(1);
		
		day.getDriverNo();
	}

	
}
