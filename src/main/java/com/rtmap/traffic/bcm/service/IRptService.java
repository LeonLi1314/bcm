package com.rtmap.traffic.bcm.service;

import java.util.List;

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

public interface IRptService {

	List<RptDriverSubsection> getRptDriverSubByCond(RptDriverCond cond);

	List<RptDriverDay> getRptDriverDayByCond(RptDriverCond cond);

	List<RptDriverHour> getRptDriverHourByCond(RptDriverCond cond);

	List<RptPassDay> getRptPassDayByCond(RptPassCond cond);

	List<RptPassDistribute> getRptPassDistributeByCond(RptPassCond cond);

	List<RptVehicleChargeDay> getRptVehicleChargeDayByCond(RptVehicleCond cond);

	List<RptVehicleChargeSub> getRptVehicleChargeSubByCond(RptVehicleCond cond);

	List<RptVehicleTripHour> getRptVehicleTripHourByCond(RptVehicleCond cond);
}
