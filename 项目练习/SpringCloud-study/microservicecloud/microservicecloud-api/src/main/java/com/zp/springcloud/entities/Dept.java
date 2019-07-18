package com.zp.springcloud.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 部门实体类
 * 
 * @author zp
 *
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Dept implements Serializable {

	private Long deptno;// 主键
	private String dname;// 部门名称
	private String db_source;// 来自哪个数据库，有可能同一个信息存储到不同数据库

	public Dept(String dname) {
		super();
		this.dname = dname;
	}

}
