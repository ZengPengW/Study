package com.zpp.domain;
/*
 * 用于封装分页的数据
 * 总记录数
 * 总页数
 * 当前页码
 * 每页显示记录数
 */

import java.util.List;

public class PageBean<T> {
	private long currentPage;//当前页
	private long totalPage;//总页数
	private long totalSize;//总记录数
	String productClass;
	private List<T> list;
	
	public PageBean() {
	
	}
	
	public PageBean(int currentPage, long totalSize, long totalPage, List<T> list,String productClass) {
		this.currentPage=currentPage;
		this.totalPage=totalPage;
		this.totalSize=totalSize;
		this.productClass=productClass;
		this.list=list;
	}

	public String getProductClass() {
		return productClass;
	}

	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}

	public long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}
	public long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
	
	public long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
}
