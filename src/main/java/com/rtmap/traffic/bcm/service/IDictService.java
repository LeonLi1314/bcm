package com.rtmap.traffic.bcm.service;

import java.util.List;

import lqs.frame.ui.model.ComboStrItem;

public interface IDictService {

	List<ComboStrItem> getBuildingSource();

	List<ComboStrItem> getDriverSource();

	List<ComboStrItem> getVehicleSource();
}
