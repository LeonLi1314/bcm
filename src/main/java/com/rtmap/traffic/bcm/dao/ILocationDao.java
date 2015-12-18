package com.rtmap.traffic.bcm.dao;

import java.util.List;

import com.rtmap.traffic.bcm.domain.Location;
import com.rtmap.traffic.bcm.domain.LocationCond;

public interface ILocationDao {
    List<Location> selectEffectCoordinatesByCond(LocationCond cond);
}