package com.zpp.domain;

//购物车
public class ShopCart {
	int pid;// 商品id
	int count;// 数量
	
	
	public ShopCart() {
		
	}
	public ShopCart(int pid, int count) {
		this.pid=pid;
		this.count=count;
		
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
