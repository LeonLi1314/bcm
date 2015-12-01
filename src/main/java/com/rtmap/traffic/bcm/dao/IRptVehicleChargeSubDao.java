package com.rtmap.traffic.bcm.dao;

import com.rtmap.traffic.bcm.domain.RptVehicleChargeSub;

public interface IRptVehicleChargeSubDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RptVehicleChargeSub record);

    int insertSelective(RptVehicleChargeSub record);

    RptVehicleChargeSub selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RptVehicleChargeSub record);

    int updateByPrimaryKey(RptVehicleChargeSub record);
}