package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;

public interface ItemParamItemService {

	/**
	 * 通过商品id 获得商品规格信息
	 */
	public TaotaoResult getItemParamItemByItemId(Long itemId);
}
