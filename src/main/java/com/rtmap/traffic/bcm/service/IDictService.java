package com.rtmap.traffic.bcm.service;

import java.util.List;

import lqs.frame.ui.model.ComboStrItem;

public interface IDictService {
	List<ComboStrItem> getBuildings();

	List<ComboStrItem> getDrivers( );

	List<ComboStrItem> getVehicles( );

	List<ComboStrItem> getBuildingsWithAll();

	List<ComboStrItem> getDriversWithAll( );

	List<ComboStrItem> getVehiclesWithAll( );
}
