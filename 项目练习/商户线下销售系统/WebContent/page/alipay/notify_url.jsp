<%@page import="com.zpp.service.PayServiceImpl"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="com.zpp.service.PayService"%>
<%@page import="com.zpp.utils.Base64Utils"%>
<%@page import="com.zpp.utils.CookiesUtils"%>
<%@page import="com.zpp.domain.Order"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="com.zpp.servlet.client.ShowTekeSocket"%>
<%@page import="java.util.concurrent.ConcurrentHashMap"%>
<%@page import="java.io.IOException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.alipay.api.internal.util.AlipaySignature"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.alipay.config.*"%>
<%@ page import="com.alipay.api.*"%>
<%
	//获取支付宝POST过来反馈信息
	Map<String,String> params = new HashMap<String,String>();
	Map requestParams = request.getParameterMap();
	if(requestParams!=null&&!requestParams.isEmpty()){
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号

			String out_trade_no = request.getParameter("out_trade_no");
			//支付宝交易号

			String trade_no = request.getParameter("trade_no");

			//交易状态
			String trade_status = request.getParameter("trade_status");

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			//计算得出通知验证结果
			//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
			boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");
			
			if(verify_result){//验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				//请在这里加上商户的业务逻辑程序代码

				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				
				if(trade_status.equals("TRADE_FINISHED")||trade_status.equals("TRADE_SUCCESS")||trade_status.equals("WAIT_BUYER_PAY")){
					
					try {
				String passback_params=request.getParameter("passback_params");
				JSONObject jsonObject=JSONObject.fromObject(Base64Utils.decoder(passback_params));
				
				Order order=(Order)JSONObject.toBean(jsonObject,Order.class);
				PayService payService=new PayServiceImpl();
				boolean flag=payService.IsExistOrder(order.getUid(), order.getEquipment(), order.getTime(), order.getGid(), order.getPhone(), order.getUsername());
						
				if(flag){
					out.clear();
					out.println("success");
					return;
				}
				boolean bl=payService.updateFinanceOnPay(order.getUid(), order.getMoney());	
				payService.addOrderOnPay(order);
				
			    String time=order.getTime()+".0";
			   
			    
			    int uid=order.getUid();
			    String equ= order.getEquipment();
				List<Order> oList=payService.GetOrderByEquipment(uid,equ);
				
				order=null;
				for (Order or : oList) {
					
					if(or.getTime().equals(time)){
						
						order=or;
						break;
					}
				}
				
				if(order!=null){
					String json=JSONArray.fromObject(order).toString();
					  ConcurrentHashMap<String, ShowTekeSocket>mywebsocket=ShowTekeSocket.getWebsocket();
					 String temp=null;
						Iterator<Map.Entry<String, ShowTekeSocket>> entries=mywebsocket.entrySet().iterator();
						
						while(entries.hasNext()){
							Map.Entry<String, ShowTekeSocket> entry=entries.next();
							if(entry.getValue().getSession().isOpen()==false){
								entries.remove();
							}else {
								String item=null;
								item=entry.getKey();
								temp = item.substring(0, item.indexOf("a"));
								
								if (temp.equals(String.valueOf(order.getUid()))) {

									try {						
										entry.getValue().sendMessage(json);
									} catch (IOException e) {
										e.printStackTrace();
										continue;
									}
								}
							}
						}
				}
			//	request.getRequestDispatcher("/page/customer/paySuccess.jsp").forward(request, response);
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}	
					
		
					
				} 

				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				out.clear();
				out.println("success");	//请不要修改或删除

				//////////////////////////////////////////////////////////////////////////////////////////
			}else{//验证失败
				out.println("fail");
			}
	}
%>
