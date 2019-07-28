package com.zp.oauth2.server.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "tb_permission")
public class TbPermission implements Serializable {

	
	private Long id;

	/**
	 * 父权限
	 */
	
	private Long parentId;

	/**
	 * 权限名称
	 */

	private String name;

	/**
	 * 权限英文名称
	 */
	
	private String enname;

	/**
	 * 备注
	 */
	
	private String description;

	
	private Date created;

	
	private Date updated;

}
