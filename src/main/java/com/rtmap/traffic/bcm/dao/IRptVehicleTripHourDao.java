package com.rtmap.traffic.bcm.dao;

import com.rtmap.traffic.bcm.domain.RptVehicleTripHour;

public interface IRptVehicleTripHourDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RptVehicleTripHour record);

    int insertSelective(RptVehicleTripHour record);

    RptVehicleTripHour selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RptVehicleTripHour record);

    int updateByPrimaryKey(RptVehicleTripHour record);
}