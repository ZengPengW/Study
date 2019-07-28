package com.zp.oauth2.server.config;

import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.zp.oauth2.server.pojo.TbPermission;
import com.zp.oauth2.server.pojo.TbUser;
import com.zp.oauth2.server.service.TbPermissionService;
import com.zp.oauth2.server.service.TbUserService;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private TbUserService tbUserService;
	
	@Autowired
	private TbPermissionService tbPermissionService;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TbUser tbUser = tbUserService.getByUserName(username);
		List<GrantedAuthority> grantedAuthorities=Lists.newArrayList();
		if (tbUser!=null) {
			List<TbPermission> permissions = tbPermissionService.selectByUserId(tbUser.getId());
			
			for (TbPermission tbPermission : permissions) {
				GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(tbPermission.getEnname());
				grantedAuthorities.add(grantedAuthority);
			}
		}
		return new User(tbUser.getUsername(),tbUser.getPassword(),grantedAuthorities);
		
		
		
		
	}

}
