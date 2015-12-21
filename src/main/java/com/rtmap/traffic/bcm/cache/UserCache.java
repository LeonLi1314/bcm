package com.rtmap.traffic.bcm.cache;

import com.rtmap.traffic.bcm.domain.SysUser;

public class UserCache {
	private static EhcacheUtil util = EhcacheUtil.getInstance();

	public static SysUser get(String userCd) {
		Object o = util.get(CacheNames.USER, userCd);
		
		return (SysUser)o;
	}

	public static void put(String userCd, SysUser value) {
		util.put(CacheNames.USER, userCd, value);
	}
	
	public static void remove(String userCd) {
		util.remove(CacheNames.USER, userCd);
	}
}
