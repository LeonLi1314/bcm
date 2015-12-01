package com.rtmap.traffic.bcm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rtmap.traffic.bcm.dao.ISysMenuDao;
import com.rtmap.traffic.bcm.dao.ISysUserDao;
import com.rtmap.traffic.bcm.domain.SysMenu;
import com.rtmap.traffic.bcm.domain.SysUser;
import com.rtmap.traffic.bcm.service.ISysUserService;

@Service
public class SysUserServiceImpl implements ISysUserService{
	
	@Resource
	ISysUserDao dao;

	@Resource
	ISysMenuDao menuDao;

	@Override
	public SysUser getUserByUsername(String userCd) {
		return dao.selectByPrimaryKey(userCd);
	}

	@Override
	public boolean updatePasswordByUserCd(String userCd,String password){
		SysUser user = new SysUser();
		user.setUserCd(userCd);
		user.setPassword(password);
		dao.updateByPrimaryKeySelective(user);
		return true;
	}

	@Override
	public List<SysMenu> getAllEnabledSysMenus(){
		List<SysMenu> menus = menuDao.selectAllEnabled();
		
		return menus;
	}
}
