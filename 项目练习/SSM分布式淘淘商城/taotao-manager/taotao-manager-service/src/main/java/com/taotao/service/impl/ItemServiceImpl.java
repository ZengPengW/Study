package com.taotao.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.manager.jedis.JedisClient;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemDescExample;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.service.ItemService;

@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
@Service
public class ItemServiceImpl implements ItemService, Serializable {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Resource(name = "topicDestination")
	private Destination destination;

	@Autowired
	private JedisClient jedisClient;

	@Value("${ITEM_INFO_KEY}")
	private String ITEM_INFO_KEY;

	@Value("${ITEM_INFO_EXPIRE}")
	private Integer ITEM_INFO_EXPIRE;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public EasyUIDataGridResult<TbItem> getItemList(Integer page, Integer rows) {
		if (page == null)
			page = 1;
		if (rows == null)
			rows = 30;
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		EasyUIDataGridResult<TbItem> dataGridResult = new EasyUIDataGridResult<>();
		dataGridResult.setRows(list);
		dataGridResult.setTotal(pageInfo.getTotal());
		return dataGridResult;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public TbItem getTbItemById(Long itemId) {
		// 从缓存中获取数据，没有就查数据库
		try {
			String jsonstr = jedisClient.get(ITEM_INFO_KEY + itemId + ":BASE");
			if (StringUtils.isNotBlank(jsonstr)) {
				// 重置有效时间
				jedisClient.expire(ITEM_INFO_KEY + itemId + ":BASE", ITEM_INFO_EXPIRE);
				return JsonUtils.jsonToPojo(jsonstr, TbItem.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);

		try {
			// 添加缓存
			jedisClient.set(ITEM_INFO_KEY + itemId + ":BASE", JsonUtils.objectToJson(tbItem));
			// 设置到期时间
			jedisClient.expire(ITEM_INFO_KEY + itemId + ":BASE", ITEM_INFO_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tbItem;
	}

	@Override
	public TaotaoResult addItem(TbItem item, String desc, String itemParams) {
		// 生成商品id
		final long itemId = IDUtils.getItemId();

		// 补全item的属性
		item.setId(itemId);
		item.setStatus((byte) 1);// 商品状态 1 正常 2下架 3 删除
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);

		// 向商品表中插入数据
		itemMapper.insert(item);
		// 创建一个商品描述表对应的pojo
		TbItemDesc itemDesc = new TbItemDesc();
		// 补全pojo的属性
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		// 向商品描述表插入数据
		itemDescMapper.insert(itemDesc);

		// 插入商品规格参数
		insertItemParamItem(item.getId(), itemParams);

		// 添加发送消息
		jmsTemplate.send(destination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				// 发送消息
				return session.createTextMessage(itemId + "");
			}
		});

		// 返回结果
		return TaotaoResult.ok();
	}

	/**
	 * 添加规格参数
	 * 
	 * @param itemId
	 * @param itemParams
	 */
	private TaotaoResult insertItemParamItem(Long itemId, String itemParams) {
		TbItemParamItem tbItemParamItem = new TbItemParamItem();
		tbItemParamItem.setCreated(new Date());
		tbItemParamItem.setUpdated(tbItemParamItem.getCreated());
		tbItemParamItem.setItemId(itemId);
		tbItemParamItem.setParamData(itemParams);
		itemParamItemMapper.insert(tbItemParamItem);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateItem(TbItem item, String desc, Long itemParamId, String itemParams) {
		//删除缓存
		try {
			jedisClient.del(ITEM_INFO_KEY + item.getId() + ":BASE");
			jedisClient.del(ITEM_INFO_KEY +  item.getId() + ":PARAM");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 补全item的属性
		Date date = new Date();
		item.setUpdated(date);

		// 向商品表中更新数据
		itemMapper.updateByPrimaryKeySelective(item);
		// 创建一个商品描述表对应的pojo
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(item.getId());
		// 补全pojo的属性
		itemDesc.setUpdated(date);
		itemDesc.setItemDesc(desc);
		// 向商品描述表更新数据
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);

		// 修改商品规格参数
		TbItemParamItem itemParamitem = new TbItemParamItem();
		itemParamitem.setId(itemParamId);
		itemParamitem.setUpdated(date);
		itemParamitem.setParamData(itemParams);

		itemParamItemMapper.updateByPrimaryKeySelective(itemParamitem);

		// 添加发送消息
		final long itemId = item.getId();
		jmsTemplate.send(destination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				// 发送消息
				return session.createTextMessage(itemId + "");
			}
		});

		// 返回结果
		return TaotaoResult.ok(null);
	}

	@Override
	public TaotaoResult deleteItem(String ids) {
		String[] idss = ids.split(",");

		List<Long> id = new ArrayList<Long>(idss.length);
		for (int i = 0; i < idss.length; i++) {
			id.add(Long.valueOf(idss[i]));
		}
		//删除缓存
		try {
			for (Long itemid : id) {
				jedisClient.del(ITEM_INFO_KEY + itemid + ":BASE");
				jedisClient.del(ITEM_INFO_KEY +  itemid + ":PARAM");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		// 删除item
		TbItemExample itemExample = new TbItemExample();
		Criteria itemCriteria = itemExample.createCriteria();
		itemCriteria.andIdIn(id);
		itemMapper.deleteByExample(itemExample);

		// 删除itemDesc
		TbItemDescExample descExample = new TbItemDescExample();
		com.taotao.pojo.TbItemDescExample.Criteria descCriteria = descExample.createCriteria();
		descCriteria.andItemIdIn(id);
		itemDescMapper.deleteByExample(descExample);

		// 删除itemParam
		TbItemParamItemExample paramItemExample = new TbItemParamItemExample();
		com.taotao.pojo.TbItemParamItemExample.Criteria paramCriteria = paramItemExample.createCriteria();
		paramCriteria.andItemIdIn(id);
		itemParamItemMapper.deleteByExample(paramItemExample);

		// 添加发送消息
		final String idS = ids;
		jmsTemplate.send(destination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				// 发送消息
				return session.createTextMessage("delete:" + idS);
			}
		});

		return TaotaoResult.ok(null);
	}

	@Override
	public TaotaoResult instockItem(String ids) {
		String[] idss = ids.split(",");

		List<Long> id = new ArrayList<Long>(idss.length);
		for (int i = 0; i < idss.length; i++) {
			id.add(Long.valueOf(idss[i]));
		}
		TbItem item = new TbItem();
		item.setStatus((byte) 2);

		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(id);
		itemMapper.updateByExampleSelective(item, example);
		return TaotaoResult.ok(null);
	}

	@Override
	public TaotaoResult reshelfItem(String ids) {
		String[] idss = ids.split(",");

		List<Long> id = new ArrayList<Long>(idss.length);
		for (int i = 0; i < idss.length; i++) {
			id.add(Long.valueOf(idss[i]));
		}
		TbItem item = new TbItem();
		item.setStatus((byte) 1);

		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(id);
		itemMapper.updateByExampleSelective(item, example);
		return TaotaoResult.ok(null);
	}

	@Override
	public String getItemParam(Long itemId) {
		TbItemParamItem itemParamItem = null;
		// 添加缓存
		// 从缓存中获取数据，没有就查数据库
		try {
			String jsonstr = jedisClient.get(ITEM_INFO_KEY + itemId + ":PARAM");
			if (StringUtils.isNotBlank(jsonstr)) {
				//System.out.println("有缓存");
				// 重置有效时间
				jedisClient.expire(ITEM_INFO_KEY + itemId + ":PARAM", ITEM_INFO_EXPIRE);
				itemParamItem = JsonUtils.jsonToPojo(jsonstr, TbItemParamItem.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 从数据库取值
		if (itemParamItem == null) {
			TbItemParamItemExample example = new TbItemParamItemExample();
			com.taotao.pojo.TbItemParamItemExample.Criteria criteria = example.createCriteria();
			criteria.andItemIdEqualTo(itemId);
			List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
			if (list != null && list.size() > 0) {
				itemParamItem = list.get(0);

				try {
					// 添加缓存
					jedisClient.set(ITEM_INFO_KEY + itemId + ":PARAM", JsonUtils.objectToJson(itemParamItem));
					// 设置到期时间
					jedisClient.expire(ITEM_INFO_KEY + itemId + ":PARAM", ITEM_INFO_EXPIRE);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

		if (itemParamItem != null) {

			String resultHtml = "";

			try {

				// 取规格参数信息
				String paramData = itemParamItem.getParamData();
				// 把规格参数转换成java对象
				List<Map> paramList = JsonUtils.jsonToList(paramData, Map.class);
				// 拼装html
				resultHtml = "<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n"
						+ "    <tbody>\n";
				for (Map map : paramList) {
					resultHtml += "        <tr>\n" + "            <th class=\"tdTitle\" colspan=\"2\">"
							+ map.get("group") + "</th>\n" + "        </tr>\n";
					List<Map> params = (List<Map>) map.get("params");
					for (Map map2 : params) {

						resultHtml += "        <tr>\n" + "            <td class=\"tdTitle\">" + map2.get("k")
								+ "</td>\n" + "            <td>" + map2.get("v") + "</td>\n" + "        </tr>\n";
					}

				}
				resultHtml += "    </tbody>\n" + "</table>";
				return resultHtml;
			} catch (Exception e) {
				// 如果转换发送异常，忽略。返回一个空字符串。
				e.printStackTrace();
			}
		}

		return "";
	}

}
