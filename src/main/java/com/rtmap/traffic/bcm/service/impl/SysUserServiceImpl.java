package com.rtmap.traffic.bcm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rtmap.traffic.bcm.cache.BaseDataCache;
import com.rtmap.traffic.bcm.cache.CacheKeys;
import com.rtmap.traffic.bcm.cache.UserCache;
import com.rtmap.traffic.bcm.dao.ISysRolePrivDao;
import com.rtmap.traffic.bcm.dao.ISysUserDao;
import com.rtmap.traffic.bcm.dao.ISysUserRoleDao;
import com.rtmap.traffic.bcm.domain.SysRolePriv;
import com.rtmap.traffic.bcm.domain.SysUser;
import com.rtmap.traffic.bcm.service.ISysUserService;

@Service
public class SysUserServiceImpl implements ISysUserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	ISysUserDao sysUserDao;
	@Resource
	ISysUserRoleDao sysUserRoleDao;
	@Resource
	ISysRolePrivDao sysRolePrivDao;

	@Override
	public SysUser getUserByUsername(String userCd) {
		SysUser user = UserCache.get(userCd);

		if (user == null) {
			user = sysUserDao.selectByPrimaryKey(userCd);
			// 加载用户对应的角色
			List<String> roleCds = sysUserRoleDao.selectRoleCdsByUserCd(userCd);

			if (roleCds == null) {
				roleCds = new ArrayList<>();
				logger.warn(String.format("用户【%s】没有配置任何角色！", userCd));
			}

			user.setRoles(roleCds);

			// 加载user对应的权限
			List<String> privCds = getPrivCdsByRoleCds(roleCds);

			if (privCds == null) {
				privCds = new ArrayList<>();
			}

			user.setPrivCds(privCds);
			UserCache.put(userCd, user);
		}

		return user;
	}

	@Override
	public boolean modifyPasswordByUserCd(String userCd, String password) {
		SysUser user = new SysUser();
		user.setUserCd(userCd);
		user.setPassword(password);
		int i = sysUserDao.updateByPrimaryKeySelective(user);

		return i == 1;
	}

	private List<String> getPrivCdsByRoleCds(List<String> roleCds) {
		List<String> rst = new ArrayList<>();

		if (roleCds == null || roleCds.size() == 0)
			return rst;

		List<SysRolePriv> list = selectAllSysRolePriv();

		for (SysRolePriv sysRolePriv : list) {
			if (!roleCds.contains(sysRolePriv.getRoleCd()))
				continue;

			if (rst.contains(sysRolePriv.getPrivCd()))
				continue;

			rst.add(sysRolePriv.getPrivCd());
		}

		return rst;
	}

	private List<SysRolePriv> selectAllSysRolePriv() {
		List<SysRolePriv> source = BaseDataCache.<SysRolePriv> get(CacheKeys.SYS_ROLE_PRIV);

		if (source == null) {
			source = sysRolePrivDao.selectAll();

			if (source == null) {
				source = new ArrayList<>();
			}

			BaseDataCache.put(CacheKeys.SYS_ROLE_PRIV, source);
		}

		return source;
	}
}
