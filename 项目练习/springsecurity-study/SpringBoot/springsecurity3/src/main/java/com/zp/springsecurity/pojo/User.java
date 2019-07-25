package com.zp.springsecurity.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class User implements Serializable,UserDetails{
	private Integer id; //int(10) NOT NULL用户id
	private String username; //varchar(50) NOT NULL用户名
	private String realname;//varchar(50) NULL真实姓名
	private String password;//varchar(50) NULL密码
	private Date createDate ;//date NULL创建日期
	private Date lastLoginTime;//date NULL最后登录时间
	private boolean enabled ;//int(5) NULL是否可用
	private boolean accountNonExpired; //int(5) NULL是否过期
	private boolean accountNonLocked; //int(5) NULL是否锁定
	private boolean credentialsNonExpired;//int(5) NULL证书是否过期
	
	
	//用户拥有的所有权限
	private  List<GrantedAuthority>  authorities =new ArrayList<GrantedAuthority>();

}
