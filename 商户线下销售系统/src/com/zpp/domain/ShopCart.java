package com.zpp.domain;

//���ﳵ
public class ShopCart {
	int pid;// ��Ʒid
	int count;// ����
	String shopname;//��Ʒ����
	double shopprice;//�۸�
	int sum;//����
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public double getShopprice() {
		return shopprice;
	}
	public void setShopprice(double shopprice) {
		this.shopprice = shopprice;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public ShopCart() {
		
	}
	public ShopCart(int pid, int count, String shopname,double shopprice,int sum) {
		this.pid=pid;
		this.count=count;
		this.shopname=shopname;
		this.shopprice=shopprice;
		this.sum=sum;
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
