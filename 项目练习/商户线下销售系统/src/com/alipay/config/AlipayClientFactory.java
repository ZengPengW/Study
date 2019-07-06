package com.alipay.config;

import java.util.HashMap;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;


import net.sf.json.JSONObject;

public class AlipayClientFactory {
	/*
	 * private static final AlipayClient client = new DefaultAlipayClient(
	 * AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY,
	 * AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY);
	 */

	private static final AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
			AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,
			AlipayConfig.SIGNTYPE);

	public static AlipayClient getAlipayClientInstance() {
		return client;
	}

	// 手机网页支付 网站支付
	public String ydAndPc_Pay(Map<String, String> maps) throws AlipayApiException {
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
		String NotifyUrl = maps.get("NotifyUrl");
		String ReturnUrl = maps.get("ReturnUrl");
		// 后台回调
		if (NotifyUrl!=null&&!NotifyUrl.isEmpty()) {
			alipayRequest.setNotifyUrl(NotifyUrl);
			// bizContent 中不需要 公共参数
			maps.remove("NotifyUrl");
		}
		// 页面回调
		if (ReturnUrl!=null&&!ReturnUrl.isEmpty()) {
			alipayRequest.setReturnUrl(ReturnUrl);
			// bizContent 中不需要 公共参数
			maps.remove("ReturnUrl");
		}
		String bizCon = JSONObject.fromObject(maps).toString();
		alipayRequest.setBizContent(bizCon);
		String form = "";
		try {
			form = AlipayClientFactory.getAlipayClientInstance().pageExecute(alipayRequest).getBody();
			
		} catch (AlipayApiException e) {
			form = "err";
			e.printStackTrace();
		} // 调用SDK生成表单
		return form;
	}

	// 电脑页面
	public String Pc_Pay(Map<String, String> maps) throws AlipayApiException {
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		String NotifyUrl = maps.get("NotifyUrl");
		String ReturnUrl = maps.get("ReturnUrl");
		// 后台回调
		if (!StringUtils.isEmpty(NotifyUrl)) {
			alipayRequest.setNotifyUrl(NotifyUrl);
			// bizContent 中不需要 公共参数
			maps.remove("NotifyUrl");
		}
		// 页面回调
		if (!StringUtils.isEmpty(ReturnUrl)) {
			alipayRequest.setReturnUrl(ReturnUrl);
			// bizContent 中不需要 公共参数
			maps.remove("ReturnUrl");
		}
		String bizCon = JSONObject.fromObject(maps).toString();
		//System.out.println("bizCon :" + bizCon);
		alipayRequest.setBizContent(bizCon);
		String form = "";
		try {
			form = AlipayClientFactory.getAlipayClientInstance().pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			form = "err";
			e.printStackTrace();
		} // 调用SDK生成表单
		return form;
	}

	// 查询订单状态
	public AlipayTradeQueryResponse query(String appAuthToken, String bizContent) throws AlipayApiException {
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.putOtherTextParam("app_auth_token", appAuthToken);
		request.setBizContent(bizContent);
		return AlipayClientFactory.getAlipayClientInstance().execute(request);
	}

	// 条码支付
	public AlipayTradePayResponse pay(String appAuthToken, String bizContent) throws AlipayApiException {
		AlipayTradePayRequest request = new AlipayTradePayRequest();
		request.putOtherTextParam("app_auth_token", appAuthToken);
		request.setBizContent(bizContent);
		return AlipayClientFactory.getAlipayClientInstance().execute(request);
	}

	// 扫码支付
	public AlipayTradePrecreateResponse precreate(String appAuthToken, String bizContent) throws AlipayApiException {
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
		request.putOtherTextParam("app_auth_token", appAuthToken);
		request.setBizContent(bizContent);
		return AlipayClientFactory.getAlipayClientInstance().execute(request);
	}

	// 订单撤销
	public AlipayTradeCancelResponse cancel(String appAuthToken, String bizContent) throws AlipayApiException {
		AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
		request.putOtherTextParam("app_auth_token", appAuthToken);
		request.setBizContent(bizContent);
		return AlipayClientFactory.getAlipayClientInstance().execute(request);
	}

	// 申请退款
	public AlipayTradeRefundResponse refund(String appAuthToken, String bizContent) throws AlipayApiException {
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.putOtherTextParam("app_auth_token", appAuthToken);
		request.setBizContent(bizContent);
		return AlipayClientFactory.getAlipayClientInstance().execute(request);
	}

//	@SuppressWarnings("unused")
//	public static void main(String[] args) {
//		AlipayClientFactory c = new AlipayClientFactory();
//		try {
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("out_trade_no", "20160914113218");
//			String bizContent = JSONObject.fromObject(map).toString();
//			System.err.println(bizContent);
//			AlipayTradeQueryResponse rp = c.query(null, bizContent);
//		} catch (AlipayApiException e) {
//			e.printStackTrace();
//		}
//	}

}
