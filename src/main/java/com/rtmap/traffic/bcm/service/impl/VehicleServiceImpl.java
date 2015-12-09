package com.rtmap.traffic.bcm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rtmap.traffic.bcm.dao.IVehicleDao;
import com.rtmap.traffic.bcm.domain.Vehicle;
import com.rtmap.traffic.bcm.service.IVehicleService;

@Service
public class VehicleServiceImpl implements IVehicleService {
	@Resource
	IVehicleDao dao ;
	
	@Override
	public List<Vehicle> getEnabledVehicles(){
		return dao.selectAllEnabled();
	}
}
