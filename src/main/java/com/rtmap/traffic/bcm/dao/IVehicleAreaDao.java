package com.rtmap.traffic.bcm.dao;

import java.util.List;

import com.rtmap.traffic.bcm.domain.VehicleArea;

public interface IVehicleAreaDao {
	List<VehicleArea> selectAll();
}