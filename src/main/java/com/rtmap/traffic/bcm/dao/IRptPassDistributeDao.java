package com.rtmap.traffic.bcm.dao;

import java.util.List;

import com.rtmap.traffic.bcm.domain.RptPassCond;
import com.rtmap.traffic.bcm.domain.RptPassDistribute;

public interface IRptPassDistributeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RptPassDistribute record);

    int insertSelective(RptPassDistribute record);

    RptPassDistribute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RptPassDistribute record);

    int updateByPrimaryKey(RptPassDistribute record);

    List<RptPassDistribute> selectByCond(RptPassCond cond);
}