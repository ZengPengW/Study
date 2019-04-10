package com.zpp.domain;

public class Order {
	int uid;
	String time;// YYYY-MM-DD HH:MM:SS
	String oid;//订单编号
	String gid;//取货编号
	double money;//付款金额
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	String username;
	String phone;
	String shopMessage;
	int isteke;//是否取货 0 1
	String equipment;//设备
	int statu;//状态 0 1 2
	
	public int getStatu() {
		return statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public Order() {
		
	}
	public Order(int uid,String time,String gid,String username,String phone,String shopMessage,String equipment,double money) {
		this.equipment=equipment;
		this.gid=gid;
		this.uid=uid;
		this.time=time;
		this.username=username;
		this.phone=phone;
		this.shopMessage=shopMessage;
		this.money=money;
	}
	public int getIsteke() {
		return isteke;
	}
	public void setIsteke(int isteke) {
		this.isteke = isteke;
	}
	public String getShopMessage() {
		return shopMessage;
	}
	public void setShopMessage(String shopMessage) {
		this.shopMessage = shopMessage;
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
