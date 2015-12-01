package com.rtmap.traffic.bcm.dao;

import com.rtmap.traffic.bcm.domain.RptDriverDay;

public interface IRptDriverDayDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RptDriverDay record);

    int insertSelective(RptDriverDay record);

    RptDriverDay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RptDriverDay record);

    int updateByPrimaryKey(RptDriverDay record);
}