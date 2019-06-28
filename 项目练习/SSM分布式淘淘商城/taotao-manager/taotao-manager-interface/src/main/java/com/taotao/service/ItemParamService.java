package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamAndCatName;

/**
 * 商品规格模版接口
 * 
 * @author zp
 *
 */
public interface ItemParamService {
	/**
	 * 查询规格模版
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	public EasyUIDataGridResult<TbItemParamAndCatName> getItemParamList(Integer page, Integer rows);

	/**
	 * 通过cid获取规格模版
	 * 
	 * @param cid
	 * @return
	 */
	public TaotaoResult getItemParamByCid(Long cid);

	/**
	 * 保存商品规格模版
	 * 
	 * @param itemParam
	 * @return
	 */
	public TaotaoResult insertItemParam(TbItemParam itemParam);

	/**
	 * 删除规格模版
	 * @param ids
	 * @return
	 */
	public TaotaoResult deleteItemParamByIds(String ids);

}
