package com.zpp.domain;

//���ﳵ
public class ShopCart {
	int pid;// ��Ʒid
	int count;// ����
	
	
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
