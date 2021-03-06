﻿package com.alipay.config;

public class AlipayConfig {
	// 商户appid
	public static String APPID = "2016092700607768";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDFbOPi4X4VYCCEsWVzgBIcsXlF2JRcVyBqM3o7Om6aQonMawMaNXWmn+S0MRxRhc0Lurts3M2rQ99DFbniMd/6teZLTlYYf7EDAEoLH6aFrt+KXQ/hRdSrlrIrgTra43jAFOAPgjJEBXJ2F3m5BJFv96IHAh9X59+w3URAfee1nPdX/j1CH7E+wdbR6eeCidnPB0TBhLpEGM71jgd1aujHY9hERGYtBxZZZfioemPnlju5ltiBxIQTChfSAVJQgjllWs+TLJf1aVw8RXE8Iy0eHwxTEmF6Gle67BK/Uw1j+rFbHSaf02hU2U+moQz8ye6rj40jV8NxRFwvjXqn9xnlAgMBAAECggEAW4z2VITKyjDMg5wb9qaiP4oROdGIYxXII1eBzCt5lLFcY14QjzvRNhaZoPHRNwC8KD9MoNv3x/7ubpaJn5oPSpzFkZBfHndAZ6rfqy7fMsj4QpqLPaT7TSLRtpjz6QNpQHGtwaNXmfGItbiHKsQyiWClU2ipjkMpmUPSXbHFHJM/GErLVmT66XbwVzTVYr6dSAqbZtLhOA9qOmqBz6ERwsGCPIQFsIskmPXqyRAaHdVTSpDdFOH2I9st4l0ySfimPirrPmvwz+fouPAvXW0VhoSN32lhT8A9vkDBsVlULp0BlkJ5AAJXYeYfcohLKPpRnBV07PpyqIElQXGtCaKkgQKBgQD4bklIJWCtxoKqlsabQ5HSYwVFy9G86/N75trPqeFAL7LnefhOWNHxMrNLTxJgQ2OdtmZhBaV+MOZVjgh4NMS9s2996dZNXAtfMAIBwwmLQSPL+U7pnni8tocG4ZS4zE+Wc/dSAdcpkEpOOQv7G+JwpwaIjs3UOvY7NgTLClLz4QKBgQDLcMVced2jlKtIPxqZiRrAKV68RnyuwvWelZjTN36yPS2KsC6b43bMC8fHv7HCx9eqyrmYvBbX8WAMYTWjo+pt7rXpBXSWxB52Ef4BimX3hGYVe8aULUJbOLNiwznZTlCGZug+aa4/jr6nmAW2S6KOs4z6FLEF0kKob++VgAsmhQKBgHyJJLlC630Rdy022ia5L59svhbn/qMbEPq6llPlkCJSBvgc34m+4rdruA93evnArm5AIoBeiUCCGOUohzxjD4EdMzXGhARRsO2trkPd1qn1JJG6z4EzTMklIvkjGh+tY/RC++cjPw355BXkaQofR1s7j33jQHE0gXzbLnnuGflhAoGBAMCxmQ6lk2yMtcvhkZVZys+2n7ffX9u8aB+EfNObHdM/pTJW/F7FjdFpWxp1FK900G9CQ5dKmKyDQUGYldDgwnzL/NuxQiRHsL6O/jOBXmGas4Kn0Yiqcc6rnyXt2o5c4mLbeCEdwmELGMTvdoGblGZr5R0+mgn0hRS7qYfGV0ltAoGANdYagZhFmgOTzbTI5hVLvWuXh7XPCr78D5IgCXNPTerhyqwdkuERAeObPCj5JYRXYR4d+Oj4BHWdY3Xdh/yEZEus+HbyyZ1Dvzq+aiqRLBwq15dtgN9BKTV3rNrWXw9YuSX1yus3TVNaUso3c/4Az0P5unEQUeb+hijjQl/cei8=";
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://47.106.67.171/Zpp/page/alipay/notify_url.jsp";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址"http://dv2pda.natappfree.cc/alipay.trade.wap.pay-JAVA-UTF-8/return_url.jsp"
	public static String return_url = "http://47.106.67.171/Zpp/MyOrderCheck";
	// 请求网关地址
	public static String URL = "https://openapi.alipaydev.com/gateway.do";//openapi.alipay.com/gateway.do//openapi.alipaydev.com/gateway.do
	
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAughHgcFUQDG0wkCSeWLuN1U0IngcZJ8FyAJAZUG9PMqrTgBlEDmKBw2CfsVXOGde3xgUQpu6tmXaCXoBNFWFgPc0BN/V/89hVOfsqN8K/RRtQ07efJiH3qC97YFIsQyIQk8Qary3jgp7VxOYbRBqkbfmwBqhC7hsoXM95cmjlwy5PQadHk9kH2Cf9wNGv6xHLVQySzLZNmB6jK+Q6o7rsJJd7kgW2mPYFgdU5rP65N190VU7Ba9VlANSov64NA9UNHpdRZSEcyfo3npcq1QkB7JaH3e8eXlWfgzH0dT3dcJiGnXIQBFirxL9q1YuGx8Vnswu1Rinf8tFveK/tbCSSwIDAQAB";
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE = "RSA2";
}
