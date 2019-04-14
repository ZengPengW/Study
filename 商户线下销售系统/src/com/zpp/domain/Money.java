package com.zpp.domain;

public class Money {
int uid;
 int amount ;//提现金额
String account;//收款账号
String payee ;//收款人
String time ;//时间
int status;//进度
int mid;
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
public int getMid() {
	return mid;
}
public void setMid(int mid) {
	this.mid = mid;
}
public Money() {
	
}
public Money(int uid,int amount,String account,String payee,String time,int status) {
	this.uid=uid;
	this.account=account;
	this.payee=payee;
	this.status=status;
	this.time=time;
	this.amount=amount;
	
}
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}

public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public String getPayee() {
	return payee;
}
public void setPayee(String payee) {
	this.payee = payee;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}

}