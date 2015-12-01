package com.rtmap.traffic.bcm.dao;

import com.rtmap.traffic.bcm.domain.RptPassDay;

public interface IRptPassDayDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RptPassDay record);

    int insertSelective(RptPassDay record);

    RptPassDay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RptPassDay record);

    int updateByPrimaryKey(RptPassDay record);
}