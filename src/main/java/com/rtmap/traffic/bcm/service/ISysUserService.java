package com.rtmap.traffic.bcm.service;

import java.util.List;

import com.rtmap.traffic.bcm.domain.SysMenu;
import com.rtmap.traffic.bcm.domain.SysUser;

public interface ISysUserService {

	SysUser getUserByUsername(String username);

	boolean updatePasswordByUserCd(String userCd, String password);

	List<SysMenu> getAllEnabledSysMenus();
	
}
