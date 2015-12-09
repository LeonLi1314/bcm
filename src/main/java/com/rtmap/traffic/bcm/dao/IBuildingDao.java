package com.rtmap.traffic.bcm.dao;

import java.util.List;

import com.rtmap.traffic.bcm.domain.Building;

public interface IBuildingDao {
    List<Building> selectAll();
}