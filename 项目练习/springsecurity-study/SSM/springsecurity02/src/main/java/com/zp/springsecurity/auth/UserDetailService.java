package com.zp.springsecurity.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zp.springsecurity.pojo.Permission;
import com.zp.springsecurity.pojo.User;
import com.zp.springsecurity.service.UserService;
/**
 * 自定义验证
 * @author zp
 *
 */
public class UserDetailService implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//查询用户
		User user = userService.findByUserName(username);
		
		if(user!=null){
		//查询权限
		List<Permission> permissions = userService.findPermissionByUsername(username);
		
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();

		for (Permission permission : permissions) {
			authorities.add(new SimpleGrantedAuthority(permission.getPermTag()));
		}
		user.setAuthorities(authorities);
		}
		
		return user;
	}

}
