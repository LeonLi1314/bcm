package com.rtmap.traffic.bcm.dao;

import java.util.List;

import com.rtmap.traffic.bcm.domain.SysUser;

public interface ISysUserRolePrivDao {	
	
	List<String> selectPrivCdsByUserCd(String userCd);

	List<SysUser> selectSysUsersByRoleCd(String roleCd);
}
