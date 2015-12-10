package com.rtmap.traffic.bcm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rtmap.traffic.bcm.dao.IRptDriverDayDao;
import com.rtmap.traffic.bcm.domain.DimensionAnalyzeDto;
import com.rtmap.traffic.bcm.domain.PassCond;
import com.rtmap.traffic.bcm.service.IGraphService;

@Service
public class GraphServiceImpl implements IGraphService {
	@Resource
	IRptDriverDayDao driverDayDao;

	@Override
	public List<DimensionAnalyzeDto> getTotalDriverWork(PassCond cond) {
		return driverDayDao.selectTotalDriverWork(cond);
	}

	@Override
	public List<DimensionAnalyzeDto> getPassCategory(PassCond cond) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DimensionAnalyzeDto> getPassPeriodCount(PassCond cond) {
		// TODO Auto-generated method stub
		return null;
	}

}
