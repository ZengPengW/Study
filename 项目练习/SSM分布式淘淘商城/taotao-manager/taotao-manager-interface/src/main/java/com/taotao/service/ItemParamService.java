package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamAndCatName;

public interface ItemParamService {
	public EasyUIDataGridResult<TbItemParamAndCatName> getItemParamList(Integer page,Integer rows);
}
