package com.zpp.domain;

//购物车
public class ShopCart {
	int pid;// 商品id
	int count;// 数量
	String shopname;//商品名称
	double shopprice;//价格
	int sum;//总数
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
