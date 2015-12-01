package com.rtmap.traffic.bcm.dao;

import java.util.List;

import com.rtmap.traffic.bcm.domain.RptVehicleChargeDay;
import com.rtmap.traffic.bcm.domain.RptVehicleCond;

public interface IRptVehicleChargeDayDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RptVehicleChargeDay record);

    int insertSelective(RptVehicleChargeDay record);

    RptVehicleChargeDay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RptVehicleChargeDay record);

    int updateByPrimaryKey(RptVehicleChargeDay record);

    List<RptVehicleChargeDay> selectByCond(RptVehicleCond cond);
}