package com.rtmap.traffic.bcm.dao;

import java.util.List;

public interface ISysUserRoleDao {    
    List<String> selectRoleCdsByUserCd(String userCd);
}