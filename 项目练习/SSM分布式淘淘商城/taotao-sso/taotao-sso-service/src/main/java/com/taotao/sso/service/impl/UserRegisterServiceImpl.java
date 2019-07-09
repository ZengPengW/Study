package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.service.UserRegisterService;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

	@Autowired
	private TbUserMapper userMapper;

	@Override
	public TaotaoResult checkData(String param, Integer type) {
		//type 类型 1 username ,2 phone ,3 email
		TbUserExample example=new TbUserExample();
		Criteria criteria = example.createCriteria();
		if(type==null)return TaotaoResult.build(400, "非法参数");
		switch (type) {
		case 1:
			if(StringUtils.isEmpty(param.trim())){
				return TaotaoResult.ok(false);
			}
			criteria.andUsernameEqualTo(param);
			break;
		case 2:
			criteria.andPhoneEqualTo(param);
			break;
		case 3:
			criteria.andEmailEqualTo(param);
			break;

		default:
			return TaotaoResult.build(400, "非法参数",false);
		}
		 List<TbUser> list = userMapper.selectByExample(example);
		 //查到数据就不可用
		 if (list!=null&&list.size()>0) {
			return TaotaoResult.ok(false);
		 }
		 
		 return TaotaoResult.ok(true);
		 
		 
		 
		 
		 
		 
		 
		 
	}

	@Override
	public TaotaoResult register(TbUser user) {
		if(StringUtils.isEmpty(user.getUsername())){
			return TaotaoResult.build(400, "注册失败，用户名为空");
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			return TaotaoResult.build(400, "注册失败，密码为空");
		}
		//校验用户名是否被注册
		TaotaoResult result1 = checkData(user.getUsername(), 1);
		if(!(boolean) result1.getData()){
			return TaotaoResult.build(400, "注册失败，用户名不可用");
		}
		//校验邮箱是否被注册
		if(StringUtils.isNotBlank(user.getEmail())){
			TaotaoResult result2 = checkData(user.getEmail(), 3);
			if(!(boolean) result2.getData()){
				return TaotaoResult.build(400, "注册失败，邮箱不可用");
			}
		}
		//校验手机是否被注册
		if(StringUtils.isNotBlank(user.getEmail())){
			TaotaoResult result3 = checkData(user.getEmail(), 2);
			if(!(boolean) result3.getData()){
				return TaotaoResult.build(400, "注册失败，手机不可用");
			}
		}
		//补全pojo
		Date date=new Date();
		user.setCreated(date);
		user.setUpdated(date);
		//对密码加密
		String md5password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5password);
		//插入数据
		userMapper.insertSelective(user);
		
		return TaotaoResult.ok();
	}

}
