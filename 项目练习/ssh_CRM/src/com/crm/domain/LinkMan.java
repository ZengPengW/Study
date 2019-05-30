package com.crm.domain;
/*
 * CREATE TABLE `cst_linkman` (
  `lkm_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '��ϵ�˱��(����)',
  `lkm_name` varchar(16) DEFAULT NULL COMMENT '��ϵ������',
  `lkm_cust_id` bigint(32) NOT NULL COMMENT '�ͻ�id',
  `lkm_gender` char(1) DEFAULT NULL COMMENT '��ϵ���Ա�',
  `lkm_phone` varchar(16) DEFAULT NULL COMMENT '��ϵ�˰칫�绰',
  `lkm_mobile` varchar(16) DEFAULT NULL COMMENT '��ϵ���ֻ�',
  `lkm_email` varchar(64) DEFAULT NULL COMMENT '��ϵ������',
  `lkm_qq` varchar(16) DEFAULT NULL COMMENT '��ϵ��qq',
  `lkm_position` varchar(16) DEFAULT NULL COMMENT '��ϵ��ְλ',
  `lkm_memo` varchar(512) DEFAULT NULL COMMENT '��ϵ�˱�ע',
  PRIMARY KEY (`lkm_id`),
  KEY `FK_cst_linkman_lkm_cust_id` (`lkm_cust_id`),
  CONSTRAINT `FK_cst_linkman_lkm_cust_id` FOREIGN KEY (`lkm_cust_id`) REFERENCES `cst_customer` (`cust_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) 
 */
public class LinkMan {
 private Long lkm_id;
 private String lkm_name;
 //private Long lkm_cust_id;
 private String lkm_gender;
 private String lkm_phone;
 private String lkm_mobile;
 private String lkm_email;
 private String lkm_qq;
 private String lkm_position;
 private String lkm_memo;

 private Customer customer;

public Long getLkm_id() {
	return lkm_id;
}

public void setLkm_id(Long lkm_id) {
	this.lkm_id = lkm_id;
}

public String getLkm_name() {
	return lkm_name;
}

public void setLkm_name(String lkm_name) {
	this.lkm_name = lkm_name;
}

public String getLkm_gender() {
	return lkm_gender;
}

public void setLkm_gender(String lkm_gender) {
	this.lkm_gender = lkm_gender;
}

public String getLkm_phone() {
	return lkm_phone;
}

public void setLkm_phone(String lkm_phone) {
	this.lkm_phone = lkm_phone;
}

public String getLkm_mobile() {
	return lkm_mobile;
}

public void setLkm_mobile(String lkm_mobile) {
	this.lkm_mobile = lkm_mobile;
}

public String getLkm_email() {
	return lkm_email;
}

public void setLkm_email(String lkm_email) {
	this.lkm_email = lkm_email;
}

public String getLkm_qq() {
	return lkm_qq;
}

public void setLkm_qq(String lkm_qq) {
	this.lkm_qq = lkm_qq;
}

public String getLkm_position() {
	return lkm_position;
}

public void setLkm_position(String lkm_position) {
	this.lkm_position = lkm_position;
}

public String getLkm_memo() {
	return lkm_memo;
}

public void setLkm_memo(String lkm_memo) {
	this.lkm_memo = lkm_memo;
}

public Customer getCustomer() {
	return customer;
}

public void setCustomer(Customer customer) {
	this.customer = customer;
}
 
 
 
}
