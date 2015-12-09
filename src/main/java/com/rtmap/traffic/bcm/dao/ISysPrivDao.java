package com.rtmap.traffic.bcm.dao;

import com.rtmap.traffic.bcm.domain.SysPriv;

public interface ISysPrivDao {
    int deleteByPrimaryKey(String privCd);

    int insert(SysPriv record);

    int insertSelective(SysPriv record);

    SysPriv selectByPrimaryKey(String privCd);

    int updateByPrimaryKeySelective(SysPriv record);

    int updateByPrimaryKey(SysPriv record);
}