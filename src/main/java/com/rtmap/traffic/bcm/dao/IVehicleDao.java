package com.rtmap.traffic.bcm.dao;

import java.util.List;

import com.rtmap.traffic.bcm.domain.Vehicle;

public interface IVehicleDao {
    int deleteByPrimaryKey(String vehicleNo);

    int insert(Vehicle record);

    int insertSelective(Vehicle record);

    Vehicle selectByPrimaryKey(String vehicleNo);

    int updateByPrimaryKeySelective(Vehicle record);

    int updateByPrimaryKey(Vehicle record);
    
    List<Vehicle> selectAllEnabled();
}