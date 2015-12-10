package com.rtmap.traffic.bcm.dao;

import java.util.List;

import com.rtmap.traffic.bcm.domain.DriverCond;
import com.rtmap.traffic.bcm.domain.RptDriverHour;

public interface IRptDriverHourDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RptDriverHour record);

    int insertSelective(RptDriverHour record);

    RptDriverHour selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RptDriverHour record);

    int updateByPrimaryKey(RptDriverHour record);

    List<RptDriverHour> selectByCond(DriverCond cond);
}