package com.rtmap.traffic.bcm.service;

import java.util.List;

import com.rtmap.traffic.bcm.domain.Vehicle;
import com.rtmap.traffic.bcm.domain.VirtualArea;

public interface IVehicleService {

	List<Vehicle> getEnabledVehicles();

	/**
	 * 根据车辆编号，获取运行区域
	 * @param vehicleNo 车辆编号
	 * @return 运行区域集合
	 */
	List<VirtualArea> getOperationAreaByVehicleNo(String vehicleNo);
}
