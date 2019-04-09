package com.zpp.domain;

public class Order {
	int uid;
	String time;// YYYY-MM-DD HH:MM:SS
	String oid;//订单编号
	String gid;//取货编号
	
	String username;
	String phone;
	String shopMessage;
	int isteke;
	String equipment;
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public Order() {
		
	}
	public Order(int uid,String time,String gid,String username,String phone,String shopMessage,String equipment) {
		this.equipment=equipment;
		this.gid=gid;
		this.uid=uid;
		this.time=time;
		this.username=username;
		this.phone=phone;
		this.shopMessage=shopMessage;
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
	int istake ;//取货否
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
	public int getIstake() {
		return istake;
	}
	public void setIstake(int istake) {
		this.istake = istake;
	}
	
}
