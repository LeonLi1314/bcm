package com.rtmap.traffic.bcm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rtmap.traffic.bcm.dao.ILocationDao;
import com.rtmap.traffic.bcm.dao.IRptDriverDayDao;
import com.rtmap.traffic.bcm.dao.IRptPassDayDao;
import com.rtmap.traffic.bcm.dao.IRptPassDistributeDao;
import com.rtmap.traffic.bcm.domain.Coordinate;
import com.rtmap.traffic.bcm.domain.DimensionAnalyzeDto;
import com.rtmap.traffic.bcm.domain.Location;
import com.rtmap.traffic.bcm.domain.LocationCond;
import com.rtmap.traffic.bcm.domain.MultiDimensionAnalyzeDto;
import com.rtmap.traffic.bcm.domain.PassCond;
import com.rtmap.traffic.bcm.domain.RptPassDay;
import com.rtmap.traffic.bcm.service.IGraphService;

import lqs.frame.util.DateUtils;

@Service
public class GraphServiceImpl implements IGraphService {
	@Resource
	IRptDriverDayDao driverDayDao;
	@Resource
	IRptPassDayDao passDayDao;
	@Resource
	IRptPassDistributeDao passDistributeDao;
	@Resource
	ILocationDao locationDao;

	@Override
	public List<MultiDimensionAnalyzeDto> getTotalDriverWork(PassCond cond) {
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
	public Map<String, List<DimensionAnalyzeDto>> getDriverPassSubtotaSection(PassCond cond) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Location> getEffectCoordinatesByCond(LocationCond cond) {
		cond.setTableName("location_" + DateUtils.formatDate(cond.getBeginTime(), "yyyy_MM_dd"));
		return locationDao.selectEffectCoordinatesByCond(cond);
	}

	@Override
	public int[][] getEffectCoordinateArrayByCond(LocationCond cond) {
		List<Location> list = getEffectCoordinatesByCond(cond);

		int length = 0;
		if (list != null) {
			length = list.size();
		} else {
			return null;
		}

		int[][] array = new int[length][2];
		for (int i = 0; i < length; i++) {
			array[i][0] = list.get(i).getxPoint();
			array[i][1] = Math.abs(list.get(i).getyPoint());
		}
		
		return array;
	}

	@Override
	public int[][] getTakePlaceArray(PassCond cond) {
		List<Coordinate> list=  passDistributeDao.selectTakePlaceArray(cond);

		int length = 0;
		if (list != null) {
			length = list.size();
		} else {
			return null;
		}

		int[][] array = new int[length][2];
		for (int i = 0; i < length; i++) {
			array[i][0] = list.get(i).getX();
			array[i][1] = Math.abs(list.get(i).getY());
		}
		
		return array;
	}
}
