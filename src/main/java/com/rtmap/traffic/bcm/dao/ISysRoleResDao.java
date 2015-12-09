package com.rtmap.traffic.bcm.dao;

import com.rtmap.traffic.bcm.domain.SysRoleRes;

public interface ISysRoleResDao {
    int deleteByPrimaryKey(SysRoleRes key);

    int insert(SysRoleRes record);

    int insertSelective(SysRoleRes record);
}