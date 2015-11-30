package com.rtmap.traffic.bcm.dao;

import com.rtmap.traffic.bcm.domain.SysMenu;

public interface ISysMenuDao {
    int deleteByPrimaryKey(String menuNo);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(String menuNo);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}