package com.rtmap.traffic.bcm.dao;

import com.rtmap.traffic.bcm.domain.SysRes;

public interface ISysResDao {
    int deleteByPrimaryKey(String resCd);

    int insert(SysRes record);

    int insertSelective(SysRes record);

    SysRes selectByPrimaryKey(String resCd);

    int updateByPrimaryKeySelective(SysRes record);

    int updateByPrimaryKey(SysRes record);
}