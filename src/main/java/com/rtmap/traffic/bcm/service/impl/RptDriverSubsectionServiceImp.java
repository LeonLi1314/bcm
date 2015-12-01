package com.rtmap.traffic.bcm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.rtmap.traffic.bcm.dao.IRptDriverSubsectionDao;
import com.rtmap.traffic.bcm.domain.RptDriverCond;
import com.rtmap.traffic.bcm.domain.RptDriverSubsection;
import com.rtmap.traffic.bcm.service.IRptDriverSubsectionService;

public class RptDriverSubsectionServiceImp implements IRptDriverSubsectionService{

	@Resource
	IRptDriverSubsectionDao dao;
	
	@Override
	public List<RptDriverSubsection> getRptByCond(RptDriverCond cond){
		
		return dao.selectByCond(cond);
		
	}
}
