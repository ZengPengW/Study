package com.taotao.sso.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.jedis.JedisClient;
import com.taotao.sso.service.UserLoginService;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private TbUserMapper userMapper;
	
	@Autowired
	private JedisClient jedisClient; 
	
	@Value("${USER_INFO}")
	private String USER_INFO;
	
	@Value("${EXPIRE_TIME}")
	private Integer EXPIRE_TIME;
	
	@Override
	public TaotaoResult login(String username, String password) {
		//校验用户名和密码是否为空
		if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
			return TaotaoResult.build(400, "用户名或密码为空");
		}
		//校验用户名
		TbUserExample example=new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		if(list==null||list.size()==0){
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		TbUser user=list.get(0);
		//校验密码
		password=DigestUtils.md5DigestAsHex(password.getBytes());
		if (!password.equals(user.getPassword())) {
			return TaotaoResult.build(400, "用户名或密码错误");	
		}
		//生成token
		String token=UUID.randomUUID().toString();
		
		//存储到redis 附加一个前缀
		//密码置空
		user.setPassword(null);
		jedisClient.set(USER_INFO+":"+token, JsonUtils.objectToJson(user));
		//设置过期时间
		jedisClient.expire(USER_INFO+":"+token, EXPIRE_TIME);
		//把token设置到cookies  在表现层中
		return TaotaoResult.ok(token);
	}

	@Override
	public TaotaoResult getUserByToken(String token) {
		String json=jedisClient.get(USER_INFO+":"+token);
		if(StringUtils.isNoneBlank(json)){
			//查到了
			TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
			jedisClient.expire(USER_INFO+":"+token, EXPIRE_TIME);
			return TaotaoResult.ok(user);
		}
		//4.如果查询不到返回400
		return TaotaoResult.build(400, "用户已过期");
	}

	@Override
	public TaotaoResult deleteUserByToken(String token) {
		jedisClient.del(USER_INFO+":"+token);
		return TaotaoResult.ok();
	}

}
