package com.rtmap.traffic.bcm.service;

import java.util.List;

import operamasks.ui.model.OmMenuModel;

public interface ISysMenuService {
	public List<OmMenuModel> getAllEnabledOmMenus();
	
	public List<OmMenuModel> getOmMenusByUserPriv(List<String> privCds);
}
