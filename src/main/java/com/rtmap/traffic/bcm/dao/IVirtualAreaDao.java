package com.rtmap.traffic.bcm.dao;

import java.util.List;

import com.rtmap.traffic.bcm.domain.VirtualArea;

public interface IVirtualAreaDao {
	List<VirtualArea> selectAll();
}