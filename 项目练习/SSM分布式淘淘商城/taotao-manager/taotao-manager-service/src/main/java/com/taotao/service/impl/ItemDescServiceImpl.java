package com.taotao.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.taotao.common.utils.JsonUtils;
import com.taotao.manager.jedis.JedisClient;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemDescExample;
import com.taotao.service.ItemDescService;

@Service
@Transactional
public class ItemDescServiceImpl implements ItemDescService {

	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${ITEM_INFO_KEY}")
	private String ITEM_INFO_KEY;
	
	@Value("${ITEM_INFO_EXPIRE}")
	private Integer ITEM_INFO_EXPIRE;
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public TbItemDesc geTbItemDescByID(Long itemId) {
		//从缓存中获取数据，没有就查数据库
				try {
					String jsonstr=jedisClient.get(ITEM_INFO_KEY+itemId+":DESC");
					if(StringUtils.isNotBlank(jsonstr)){
						//重置有效时间
						jedisClient.expire(ITEM_INFO_KEY+itemId+":DESC", ITEM_INFO_EXPIRE);
						return JsonUtils.jsonToPojo(jsonstr, TbItemDesc.class);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		//缓存无数据从数据库中取值
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		

		try {
			//添加缓存
			jedisClient.set(ITEM_INFO_KEY+itemId+":DESC", JsonUtils.objectToJson(itemDesc));
			//设置到期时间
			jedisClient.expire(ITEM_INFO_KEY+itemId+":DESC", ITEM_INFO_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemDesc;
	}

}
