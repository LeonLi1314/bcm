package com.rtmap.traffic.bcm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rtmap.traffic.bcm.dao.IRptDriverDayDao;
import com.rtmap.traffic.bcm.dao.IRptPassDayDao;
import com.rtmap.traffic.bcm.domain.DimensionAnalyzeDto;
import com.rtmap.traffic.bcm.domain.PassCond;
import com.rtmap.traffic.bcm.domain.RptPassDay;
import com.rtmap.traffic.bcm.service.IGraphService;

@Service
public class GraphServiceImpl implements IGraphService {
	@Resource
	IRptDriverDayDao driverDayDao;
	@Resource
	IRptPassDayDao passDayDao;

	@Override
	public List<DimensionAnalyzeDto> getTotalDriverWork(PassCond cond) {
		return driverDayDao.selectTotalDriverWork(cond);
	}

	@Override
	public List<DimensionAnalyzeDto> getPassFlowVolume(PassCond cond) {
		return passDayDao.selectPassFlowVolume(cond);
	}

	@Override
	public List<DimensionAnalyzeDto> getPassSubtotal(PassCond cond) {
		RptPassDay subTotal = passDayDao.selectPassSubtotal(cond);
		
		List<DimensionAnalyzeDto> rst = new ArrayList<>();
		DimensionAnalyzeDto item = new DimensionAnalyzeDto();
		item.setName("总乘客数");
		item.setValue(subTotal.getPassCount().toString());
		rst.add(item);

		item = new DimensionAnalyzeDto();
		item.setName("扫描乘客数");
		item.setValue(subTotal.getScanPassCount().toString());
		rst.add(item);
		
		item = new DimensionAnalyzeDto();
		item.setName("拍照乘客数");
		item.setValue(subTotal.getPhotoPassCount().toString());
		rst.add(item);
		
		item = new DimensionAnalyzeDto();
		item.setName("手动+1乘客数");
		item.setValue(subTotal.getManualAddPassCount().toString());
		rst.add(item);

		item = new DimensionAnalyzeDto();
		item.setName("急客数");
		item.setValue(subTotal.getScanHurriedPassCount().toString());
		rst.add(item);
		
		return rst;
	}

	@Override
	public Map<String,List<DimensionAnalyzeDto>> getDriverPassSubtotaSection(PassCond cond) {
		// TODO Auto-generated method stub
		return null;
	}

}
