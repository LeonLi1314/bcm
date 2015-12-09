package com.rtmap.traffic.bcm.service;

import java.util.List;

import com.rtmap.traffic.bcm.domain.Vehicle;

public interface IVehicleService {

	List<Vehicle> getEnabledVehicles();

}
