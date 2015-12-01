package com.rtmap.traffic.bcm.dao;

import com.rtmap.traffic.bcm.domain.RptVehicleChargeDay;

public interface IRptVehicleChargeDayDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RptVehicleChargeDay record);

    int insertSelective(RptVehicleChargeDay record);

    RptVehicleChargeDay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RptVehicleChargeDay record);

    int updateByPrimaryKey(RptVehicleChargeDay record);
}