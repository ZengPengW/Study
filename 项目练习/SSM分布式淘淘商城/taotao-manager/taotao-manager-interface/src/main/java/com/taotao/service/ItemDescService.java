package com.taotao.service;

import com.taotao.pojo.TbItemDesc;

public interface ItemDescService {

	/**
	 * 根据商品id返回商品详情
	 * @param ItemDescId
	 * @return
	 */
	public TbItemDesc geTbItemDescByID(Long ItemId);
}
