package com.taotao.common.pojo;

import java.io.Serializable;
/**
 * datagrid 展示数据pojo
 * @author zp
 *
 */
import java.util.List;
public class EasyUIDataGridResult<T> implements Serializable {
	private Long total;
	private List<T>rows;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
}
