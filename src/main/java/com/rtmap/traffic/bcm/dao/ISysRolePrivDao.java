package com.rtmap.traffic.bcm.dao;

import com.rtmap.traffic.bcm.domain.SysRolePrivKey;

public interface ISysRolePrivDao {
    int deleteByPrimaryKey(SysRolePrivKey key);

    int insert(SysRolePrivKey record);

    int insertSelective(SysRolePrivKey record);
}