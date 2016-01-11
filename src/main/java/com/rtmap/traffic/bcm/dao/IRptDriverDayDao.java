package com.rtmap.traffic.bcm.dao;

import java.util.List;

import com.rtmap.traffic.bcm.domain.DimensionAnalyzeDto;
import com.rtmap.traffic.bcm.domain.DriverCond;
import com.rtmap.traffic.bcm.domain.MultiDimensionAnalyzeDto;
import com.rtmap.traffic.bcm.domain.PassCond;
import com.rtmap.traffic.bcm.domain.RptDriverDay;

public interface IRptDriverDayDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RptDriverDay record);

    int insertSelective(RptDriverDay record);

    RptDriverDay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RptDriverDay record);

    int updateByPrimaryKey(RptDriverDay record);
    
    List<RptDriverDay> selectByCond(DriverCond cond);

	List<MultiDimensionAnalyzeDto> selectTotalDriverWork(PassCond cond);

	List<DimensionAnalyzeDto> selectDriverWorkBuildingSum(PassCond cond);
}