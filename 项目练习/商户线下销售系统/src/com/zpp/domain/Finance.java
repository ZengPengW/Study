package com.zpp.domain;

public class Finance {
	int uid;
	String pay;//支付宝
	int soldout;//总售出
	double balance;//余额
	double total;//总盈利
	int version;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public int getSoldout() {
		return soldout;
	}
	public void setSoldout(int soldout) {
		this.soldout = soldout;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
}
