package com.zpp.domain;

public class ShopCartBean {
Product product;
int count;
public ShopCartBean() {
}
public ShopCartBean(Product product, int count) {
	this.product=product;
	this.count=count;
}
public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
}
