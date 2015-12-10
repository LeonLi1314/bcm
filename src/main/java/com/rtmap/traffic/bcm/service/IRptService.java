package com.rtmap.traffic.bcm.service;

import java.util.List;

import com.rtmap.traffic.bcm.domain.DriverCond;
import com.rtmap.traffic.bcm.domain.RptDriverDay;
import com.rtmap.traffic.bcm.domain.RptDriverHour;
import com.rtmap.traffic.bcm.domain.RptDriverSubsection;
import com.rtmap.traffic.bcm.domain.PassCond;
import com.rtmap.traffic.bcm.domain.RptPassDay;
import com.rtmap.traffic.bcm.domain.RptPassDistribute;
import com.rtmap.traffic.bcm.domain.RptVehicleChargeDay;
import com.rtmap.traffic.bcm.domain.RptVehicleChargeSub;
import com.rtmap.traffic.bcm.domain.VehicleCond;
import com.rtmap.traffic.bcm.domain.RptVehicleTripHour;

public interface IRptService {

	List<RptDriverSubsection> getRptDriverSubByCond(DriverCond cond);

	List<RptDriverDay> getRptDriverDayByCond(DriverCond cond);

	List<RptDriverHour> getRptDriverHourByCond(DriverCond cond);

	List<RptPassDay> getRptPassDayByCond(PassCond cond);

	List<RptPassDistribute> getRptPassDistributeByCond(PassCond cond);

	List<RptVehicleChargeDay> getRptVehicleChargeDayByCond(VehicleCond cond);

	List<RptVehicleChargeSub> getRptVehicleChargeSubByCond(VehicleCond cond);

	List<RptVehicleTripHour> getRptVehicleTripHourByCond(VehicleCond cond);
}
