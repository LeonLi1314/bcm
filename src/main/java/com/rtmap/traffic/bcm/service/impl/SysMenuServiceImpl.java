package com.rtmap.traffic.bcm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rtmap.traffic.bcm.cache.BaseDataCache;
import com.rtmap.traffic.bcm.cache.CacheKeys;
import com.rtmap.traffic.bcm.dao.ISysMenuDao;
import com.rtmap.traffic.bcm.domain.SysMenu;
import com.rtmap.traffic.bcm.service.ISysMenuService;

import operamasks.ui.model.OmMenu;
import operamasks.ui.model.OmMenuModel;

@Service
public class SysMenuServiceImpl implements ISysMenuService {
	@Resource
	ISysMenuDao sysMenuDao;

	@Override
	public List<OmMenuModel> getAllEnabledOmMenus() {
		List<SysMenu> source = getAllEnabled();
		return OmMenu.convertToOmMenuModel(source);
	}

	@Override
	public List<OmMenuModel> getOmMenusByUserPriv(List<String> privCds) {
		if (privCds == null || privCds.size() == 0)
			return new ArrayList<OmMenuModel>();

		List<SysMenu> list = new ArrayList<>();
		List<SysMenu> menus = getAllEnabled();
		List<String> parentMenuNos = new ArrayList<>();

		for (SysMenu sysMenu : menus) {
			if (privCds.contains(sysMenu.getPrivCd())) {
				list.add(sysMenu);
				
				//获取子节点的父级菜单编号
				if (!parentMenuNos.contains(sysMenu.getParentMenuNo())) {
					parentMenuNos.add(sysMenu.getParentMenuNo());
				}
			}
		}

		// 增加父节点菜单，支持两级菜单
		for (SysMenu sysMenu : menus) {
			if (parentMenuNos.contains(sysMenu.getMenuNo()))
				list.add(sysMenu);
		}

		return OmMenu.convertToOmMenuModel(list);
	}

	private List<SysMenu> getAllEnabled() {
		List<SysMenu> source = BaseDataCache.<SysMenu> get(CacheKeys.SYS_MENU);

		if (source == null) {
			source = sysMenuDao.selectAllEnabled();

			if (source == null)
				source = new ArrayList<>();
		}

		BaseDataCache.put(CacheKeys.SYS_MENU, source);

		return source;
	}
}
