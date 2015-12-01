package com.rtmap.traffic.bcm.service;

import java.util.List;

import com.rtmap.traffic.bcm.domain.RptDriverCond;
import com.rtmap.traffic.bcm.domain.RptDriverSubsection;

public interface IRptDriverSubsectionService {

	List<RptDriverSubsection> getRptByCond(RptDriverCond cond);

}
