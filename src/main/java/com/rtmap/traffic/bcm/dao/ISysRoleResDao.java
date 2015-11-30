package com.rtmap.traffic.bcm.dao;

import com.rtmap.traffic.bcm.domain.SysRoleResKey;

public interface ISysRoleResDao {
    int deleteByPrimaryKey(SysRoleResKey key);

    int insert(SysRoleResKey record);

    int insertSelective(SysRoleResKey record);
}