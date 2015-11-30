package com.rtmap.traffic.bcm.service;

import com.rtmap.traffic.bcm.domain.SysUser;

public interface ISysUserService {

	SysUser getUserByUsername(String username);
	
}
