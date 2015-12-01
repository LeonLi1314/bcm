package com.rtmap.traffic.bcm.dao;

import java.util.List;

import com.rtmap.traffic.bcm.domain.RptDriverCond;
import com.rtmap.traffic.bcm.domain.RptDriverSubsection;

public interface IRptDriverSubsectionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RptDriverSubsection record);

    int insertSelective(RptDriverSubsection record);

    RptDriverSubsection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RptDriverSubsection record);

    int updateByPrimaryKey(RptDriverSubsection record);
    
    List<RptDriverSubsection> selectByCond(RptDriverCond cond);
}