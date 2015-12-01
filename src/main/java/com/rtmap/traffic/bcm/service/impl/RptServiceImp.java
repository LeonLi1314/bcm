package com.rtmap.traffic.bcm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.rtmap.traffic.bcm.dao.IRptDriverDayDao;
import com.rtmap.traffic.bcm.dao.IRptDriverHourDao;
import com.rtmap.traffic.bcm.dao.IRptDriverSubsectionDao;
import com.rtmap.traffic.bcm.dao.IRptPassDayDao;
import com.rtmap.traffic.bcm.dao.IRptPassDistributeDao;
import com.rtmap.traffic.bcm.dao.IRptVehicleChargeDayDao;
import com.rtmap.traffic.bcm.dao.IRptVehicleChargeSubDao;
import com.rtmap.traffic.bcm.dao.IRptVehicleTripHourDao;
import com.rtmap.traffic.bcm.domain.RptDriverCond;
import com.rtmap.traffic.bcm.domain.RptDriverDay;
import com.rtmap.traffic.bcm.domain.RptDriverHour;
import com.rtmap.traffic.bcm.domain.RptDriverSubsection;
import com.rtmap.traffic.bcm.domain.RptPassCond;
import com.rtmap.traffic.bcm.domain.RptPassDay;
import com.rtmap.traffic.bcm.domain.RptPassDistribute;
import com.rtmap.traffic.bcm.domain.RptVehicleChargeDay;
import com.rtmap.traffic.bcm.domain.RptVehicleChargeSub;
import com.rtmap.traffic.bcm.domain.RptVehicleCond;
import com.rtmap.traffic.bcm.domain.RptVehicleTripHour;
import com.rtmap.traffic.bcm.service.IRptService;

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

	@Override
	public List<RptDriverSubsection> getRptDriverSubByCond(RptDriverCond cond) {
		return driverSubDao.selectByCond(cond);
	}

	@Override
	public List<RptDriverDay> getRptDriverDayByCond(RptDriverCond cond) {
		return driverDayDao.selectByCond(cond);
	}

	@Override
	public List<RptDriverHour> getRptDriverHourByCond(RptDriverCond cond) {
		return driverHourDao.selectByCond(cond);
	}

	@Override
	public List<RptPassDay> getRptPassDayByCond(RptPassCond cond) {
		return passDayDao.selectByCond(cond);
	}

	@Override
	public List<RptPassDistribute> getRptPassDistributeByCond(RptPassCond cond) {
		return passDistributeDao.selectByCond(cond);
	}

	@Override
	public List<RptVehicleChargeDay> getRptVehicleChargeDayByCond(RptVehicleCond cond) {
		return vehicleDayDao.selectByCond(cond);
	}

	@Override
	public List<RptVehicleChargeSub> getRptVehicleChargeSubByCond(RptVehicleCond cond) {
		return vehicleSubDao.selectByCond(cond);
	}

	@Override
	public List<RptVehicleTripHour> getRptVehicleTripHourByCond(RptVehicleCond cond) {
		return vehicleHourDao.selectByCond(cond);
	}
}
