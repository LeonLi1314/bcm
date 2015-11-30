package com.rtmap.traffic.bcm.dao;

import com.rtmap.traffic.bcm.domain.SysUserRoleKey;

public interface ISysUserRoleDao {
    int deleteByPrimaryKey(SysUserRoleKey key);

    int insert(SysUserRoleKey record);

    int insertSelective(SysUserRoleKey record);
}