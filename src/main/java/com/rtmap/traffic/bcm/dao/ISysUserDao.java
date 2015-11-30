package com.rtmap.traffic.bcm.dao;

import com.rtmap.traffic.bcm.domain.SysUser;

public interface ISysUserDao {
    int deleteByPrimaryKey(String userCd);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String userCd);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}