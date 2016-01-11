package com.rtmap.traffic.bcm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rtmap.traffic.bcm.dao.IRptDriverDayDao;
import com.rtmap.traffic.bcm.dao.IRptDriverHourDao;
import com.rtmap.traffic.bcm.dao.IRptDriverSubsectionDao;
import com.rtmap.traffic.bcm.dao.IRptPassDayDao;
import com.rtmap.traffic.bcm.dao.IRptPassDistributeDao;
import com.rtmap.traffic.bcm.dao.IRptVehicleChargeDayDao;
import com.rtmap.traffic.bcm.dao.IRptVehicleChargeSubDao;
import com.rtmap.traffic.bcm.dao.IRptVehicleTripHourDao;
import com.rtmap.traffic.bcm.domain.DriverCond;
import com.rtmap.traffic.bcm.domain.PassCond;
import com.rtmap.traffic.bcm.domain.RptDriverDay;
import com.rtmap.traffic.bcm.domain.RptDriverHour;
import com.rtmap.traffic.bcm.domain.RptDriverSubsection;
import com.rtmap.traffic.bcm.domain.RptPassDay;
import com.rtmap.traffic.bcm.domain.RptPassDistribute;
import com.rtmap.traffic.bcm.domain.RptVehicleChargeDay;
import com.rtmap.traffic.bcm.domain.RptVehicleChargeSub;
import com.rtmap.traffic.bcm.domain.RptVehicleTripHour;
import com.rtmap.traffic.bcm.domain.VehicleCond;
import com.rtmap.traffic.bcm.service.IRptService;

@Service
public class RptServiceImp implements IRptService{

	@Resource
	IRptDriverSubsectionDao driverSubDao;
	@Resource
	IRptDriverDayDao driverDayDao;
	@Resource
	IRptDriverHourDao driverHourDao;
	@Resource
	IRptPassDayDao passDayDao;
	@Resource
	IRptPassDistributeDao passDistributeDao;
	@Resource
	IRptVehicleChargeDayDao vehicleDayDao;
	@Resource
	IRptVehicleChargeSubDao vehicleSubDao;
	@Resource
	IRptVehicleTripHourDao vehicleHourDao;
	@Resource
	VirtualAreaMatcher virtualAreaMatcher;

	@Override
	public List<RptDriverSubsection> getRptDriverSubByCond(DriverCond cond) {
		List<RptDriverSubsection> list = driverSubDao.selectByCond(cond);
		return list;
	}

	@Override
	public List<RptDriverDay> getRptDriverDayByCond(DriverCond cond) {
		return driverDayDao.selectByCond(cond);
	}

	@Override
	public List<RptDriverHour> getRptDriverHourByCond(DriverCond cond) {
		return driverHourDao.selectByCond(cond);
	}

	@Override
	public List<RptPassDay> getRptPassDayByCond(PassCond cond) {
		return passDayDao.selectByCond(cond);
	}

	@Override
	public List<RptPassDistribute> getRptPassDistributeByCond(PassCond cond) {
		List<RptPassDistribute> list =passDistributeDao.selectByCond(cond);
		
		if(list == null)
			return list;
		
		for (RptPassDistribute rptPassDistribute : list) {
			rptPassDistribute.setInArea(virtualAreaMatcher.caculateInArea(rptPassDistribute.getTakeDistance()));
		}
		
		return list;
	}

	@Override
	public List<RptVehicleChargeDay> getRptVehicleChargeDayByCond(VehicleCond cond) {
		return vehicleDayDao.selectByCond(cond);
	}

	@Override
	public List<RptVehicleChargeSub> getRptVehicleChargeSubByCond(VehicleCond cond) {
		return vehicleSubDao.selectByCond(cond);
	}

	@Override
	public List<RptVehicleTripHour> getRptVehicleTripHourByCond(VehicleCond cond) {
		return vehicleHourDao.selectByCond(cond);
	}
}
