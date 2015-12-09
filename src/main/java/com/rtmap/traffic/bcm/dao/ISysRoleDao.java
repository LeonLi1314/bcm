package com.rtmap.traffic.bcm.dao;

import com.rtmap.traffic.bcm.domain.SysRole;

public interface ISysRoleDao {
    int insert(SysRole record);

    int insertSelective(SysRole record);
}