package com.zp.springsecurity.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Permission implements Serializable{
	private Integer id;//int(11) NOT NULL权限id
	private String permName;//varchar(50) NULL权限名
	private String permTag;//varchar(50) NULL权限标识符
}
