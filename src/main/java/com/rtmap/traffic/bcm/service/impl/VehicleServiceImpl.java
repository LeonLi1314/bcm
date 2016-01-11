package com.rtmap.traffic.bcm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rtmap.traffic.bcm.dao.IVehicleAreaDao;
import com.rtmap.traffic.bcm.dao.IVehicleDao;
import com.rtmap.traffic.bcm.dao.IVirtualAreaDao;
import com.rtmap.traffic.bcm.domain.Vehicle;
import com.rtmap.traffic.bcm.domain.VehicleArea;
import com.rtmap.traffic.bcm.domain.VirtualArea;
import com.rtmap.traffic.bcm.service.IVehicleService;

import lqs.frame.util.StringUtils;

@Service
public class VehicleServiceImpl implements IVehicleService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	IVehicleDao dao;
	@Resource
	IVehicleAreaDao vehicleAreaDao;
	@Resource
	IVirtualAreaDao virtualAreaDao;

	@Override
	public List<Vehicle> getEnabledVehicles() {
		return dao.selectAllEnabled();
	}

	@Override
	public List<VirtualArea> getOperationAreaByVehicleNo(String vehicleNo) {
		// 加入缓存
		List<VehicleArea> vehicleAreas = vehicleAreaDao.selectAll();
		List<VirtualArea> rst = new ArrayList<>();

		if(vehicleAreas == null || vehicleAreas.size() == 0){
			logger.error("车辆区域配置信息表为空！");
			return rst;
		}
		
		String areaCode = "";

		for (VehicleArea vehicleArea : vehicleAreas) {
			if (vehicleArea.getVehicleNo().equals(vehicleNo)) {
				areaCode = vehicleArea.getAreaCode();

				break;
			}
		}

		if (StringUtils.isNullOrEmpty(areaCode)) {
			logger.error(String.format("车辆%s的虚拟区域配置信息为空！", vehicleNo));

			return rst;
		}

		// 加入缓存
		List<VirtualArea> virtualAreas = virtualAreaDao.selectAll();

		if(virtualAreas == null || virtualAreas.size() == 0){
			logger.error("虚拟区域配置信息表为空！");
			return rst;
		}
		
		for (VirtualArea virtualArea : virtualAreas) {
			if (virtualArea.getAreaCode().equals(areaCode)) {
				rst.add(virtualArea);
			}
		}

		if (rst.size() == 0) {
			logger.error(String.format("虚拟区域%s配置信息为空！", areaCode));
		}

		// 以车辆编号为键，加入缓存
		return rst;
	}
}
