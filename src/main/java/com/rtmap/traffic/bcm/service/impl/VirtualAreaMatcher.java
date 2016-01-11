package com.rtmap.traffic.bcm.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rtmap.traffic.bcm.domain.VirtualArea;

/**
 * 虚拟区域匹配器
 * 
 * @author liqingshan 2016-01-04
 *
 */
@Component
public class VirtualAreaMatcher {
	private int maxDistance;
	
	public int getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}

	public int caculateInArea(int distance){
		if(distance > maxDistance)
			return 0;
		
		return 1;
	}

	public int caculateInArea(int x, int y, List<VirtualArea> areas) {
		for (VirtualArea virtualArea : areas) {
			if (x <= virtualArea.getBrXPoint() && x >= virtualArea.getTlXPoint() && y <= virtualArea.getBrYPoint()
					&& y >= virtualArea.getTlYPoint())
				return 1;
		}

		return 0;
	}
}
