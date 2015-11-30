package com.rtmap.traffic.bcm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rtmap.traffic.bcm.dao.ISysUserDao;
import com.rtmap.traffic.bcm.domain.SysUser;
import com.rtmap.traffic.bcm.service.ISysUserService;

@Service
public class SysUserServiceImpl implements ISysUserService{
	
	@Resource
	ISysUserDao dao;

	@Override
	public SysUser getUserByUsername(String username) {
		return dao.selectByPrimaryKey(username);
	}

}
