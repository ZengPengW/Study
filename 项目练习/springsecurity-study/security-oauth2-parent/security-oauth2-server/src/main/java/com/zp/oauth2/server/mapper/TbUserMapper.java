package com.zp.oauth2.server.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zp.oauth2.server.pojo.TbUser;

import tk.mybatis.mapper.MyMapper;

@Mapper
public interface TbUserMapper extends MyMapper<TbUser> {

}
