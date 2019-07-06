package com.zpp.domain;

public class Product {
	int uid;
	String productName;
	double price;
	int productCount;
	String productImg;
	String productMessage;
	String productClass;
	int pid;
	public Product() {
	}

	public Product(int uid, String productName, double price, int productCount, String productImg,
			String productMessage, String productClass) {
		this.uid=uid;
		this.productName=productName;
		this.price=price;
		this.productCount=productCount;
		this.productImg=productImg;
		this.productMessage=productMessage;
		this.productClass=productClass;

	}

	public String getProductClass() {
		return productClass;
	}

	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}

	

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getProductMessage() {
		return productMessage;
	}

	public void setProductMessage(String productMessage) {
		this.productMessage = productMessage;
	}

}
