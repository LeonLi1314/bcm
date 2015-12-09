package com.rtmap.traffic.bcm.dao;

import java.util.List;

import com.rtmap.traffic.bcm.domain.SysRolePriv;

public interface ISysRolePrivDao {
    int deleteByPrimaryKey(SysRolePriv key);

    int insert(SysRolePriv record);

    int insertSelective(SysRolePriv record);
    
    List<SysRolePriv> selectAll();
    
    List<String> selectPrivCdsByRoleCd(String roleCd);

    List<String> selectPrivCdsByRoleCds(List<String> roleCds);
}