package com.zp.springsecurity.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Role implements Serializable {
	private Integer id;//int(11) NOT NULL角色id
	private String roleName;//varchar(50) NOT NULL角色名
	private String roleDesc;//varchar(50) NOT NULL角色说明
}
