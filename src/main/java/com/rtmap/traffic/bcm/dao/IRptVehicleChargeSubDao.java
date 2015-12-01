package com.rtmap.traffic.bcm.dao;

import java.util.List;

import com.rtmap.traffic.bcm.domain.RptVehicleChargeSub;
import com.rtmap.traffic.bcm.domain.RptVehicleCond;

public interface IRptVehicleChargeSubDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RptVehicleChargeSub record);

    int insertSelective(RptVehicleChargeSub record);

    RptVehicleChargeSub selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RptVehicleChargeSub record);

    int updateByPrimaryKey(RptVehicleChargeSub record);

    List<RptVehicleChargeSub> selectByCond(RptVehicleCond cond);
}