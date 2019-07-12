package com.taotao.cart.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

/**
 * 购物车管理
 * 
 * @author zp
 *
 */
@Controller
public class CartController {

	@Value("${TT_CART_KEY}")
	private String TT_CART_KEY;

	@Value("${TT_TOKEN_KEY}")
	private String TT_TOKEN_KEY;

	@Value("${CART_EXPIER}")
	private Integer CART_EXPIER;

	@Autowired
	private ItemService itemService;

	@RequestMapping("/cart/add/{itemId}")
	public String addItemCart(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		// 取购物车商品列表
		List<TbItem> cartItemList = getCartItemList(request);
		// 判断商品在购物车中是否存在
		boolean flag = false;
		for (TbItem tbItem : cartItemList) {
			if (itemId.longValue() == tbItem.getId()) {
				// 数量加一
				tbItem.setNum(num + tbItem.getNum());
				flag = true;
				break;
			}
		}
		// 不存在
		if (!flag) {
			TbItem tbItem = itemService.getTbItemById(itemId);
			tbItem.setNum(num);
			// 取一张图片
			String image = tbItem.getImage();
			if (StringUtils.isNotBlank(image)) {
				String[] split = image.split(",");
				tbItem.setImage(split[0]);
			}
			// 把商品添加购物车
			cartItemList.add(tbItem);
		}
		// 把购物车列表写入cookie
		CookieUtils.setCookie(request, response, TT_CART_KEY, JsonUtils.objectToJson(cartItemList), CART_EXPIER, true);

		// 返回成功页面
		return "cartSuccess";
	}

	/**
	 * 返回购物车列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cart/cart")
	public String showCartList(HttpServletRequest request) {
		List<TbItem> cartItemList = getCartItemList(request);
		request.setAttribute("cartList", cartItemList);
		return "cart";

	}

	/**
	 * 更新购物车
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cart/update/num/{itemId}/{num}")
	@ResponseBody
	public TaotaoResult updateItemNum(@PathVariable Long itemId, @PathVariable Integer num, HttpServletRequest request,
			HttpServletResponse response) {
		// 从cookie 中取购物车列表
		List<TbItem> cartItemList = getCartItemList(request);

		for (TbItem tbItem : cartItemList) {
			if (itemId.longValue() == tbItem.getId()) {
				tbItem.setNum(num);
				break;
			}
		}
		CookieUtils.setCookie(request, response, TT_CART_KEY, JsonUtils.objectToJson(cartItemList), CART_EXPIER, true);
		return TaotaoResult.ok();
	}

	@RequestMapping("/service/cart/update/num/{itemId}/{num}")
	@ResponseBody
	public TaotaoResult updateItemNumText(@PathVariable Long itemId, @PathVariable Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		// 从cookie 中取购物车列表
		List<TbItem> cartItemList = getCartItemList(request);

		for (TbItem tbItem : cartItemList) {
			if (itemId.longValue() == tbItem.getId()) {
				tbItem.setNum(num);
				break;
			}
		}
		CookieUtils.setCookie(request, response, TT_CART_KEY, JsonUtils.objectToJson(cartItemList), CART_EXPIER, true);
		return TaotaoResult.ok();
	}

	@RequestMapping("/cart/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
		// 从cookie中取购物车列表
		// 找到对应的商品
		List<TbItem> cartItemList = getCartItemList(request);
		for (Iterator iterator = cartItemList.iterator(); iterator.hasNext();) {
			TbItem tbItem = (TbItem) iterator.next();
			// 删除商品
			if (itemId.longValue() == tbItem.getId()) {
				iterator.remove();
				break;
			}
		}
		CookieUtils.setCookie(request, response, TT_CART_KEY, JsonUtils.objectToJson(cartItemList), CART_EXPIER, true);
		return "redirect:/cart/cart.html";

	}

	private List<TbItem> getCartItemList(HttpServletRequest request) {

		// 从cookies取购物车列表值
		String json = CookieUtils.getCookieValue(request, TT_CART_KEY, true);
		if (StringUtils.isBlank(json)) {
			return new ArrayList<>();
		}
		return JsonUtils.jsonToList(json, TbItem.class);

	}
}
