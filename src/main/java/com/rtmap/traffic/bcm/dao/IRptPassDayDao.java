package com.rtmap.traffic.bcm.dao;

import java.util.List;

import com.rtmap.traffic.bcm.domain.PassCond;
import com.rtmap.traffic.bcm.domain.RptPassDay;

public interface IRptPassDayDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RptPassDay record);

    int insertSelective(RptPassDay record);

    RptPassDay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RptPassDay record);

    int updateByPrimaryKey(RptPassDay record);

    List<RptPassDay> selectByCond(PassCond cond);
}