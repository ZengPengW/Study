package com.zp.oauth2.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zp.oauth2.server.pojo.TbPermission;

import tk.mybatis.mapper.MyMapper;

@Mapper
public interface TbPermissionMapper extends MyMapper<TbPermission>{
	 List<TbPermission> selectByUserId( Long userId);
}
