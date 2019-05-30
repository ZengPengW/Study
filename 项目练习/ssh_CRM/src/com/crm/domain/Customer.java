package com.crm.domain;

import java.util.HashSet;
import java.util.Set;

/*
 * �ͻ�ʵ��
 * CREATE TABLE `cst_customer` (
  `cust_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '�ͻ����(����)',
  `cust_name` varchar(32) NOT NULL COMMENT '�ͻ�����(��˾����)',
  `cust_source` varchar(32) DEFAULT NULL COMMENT '�ͻ���Ϣ��Դ',
  `cust_industry` varchar(32) DEFAULT NULL COMMENT '�ͻ�������ҵ',
  `cust_level` varchar(32) DEFAULT NULL COMMENT '�ͻ�����',
  `cust_phone` varchar(64) DEFAULT NULL COMMENT '�̶��绰',
  `cust_mobile` varchar(16) DEFAULT NULL COMMENT '�ƶ��绰',
  PRIMARY KEY (`cust_id`)
)
 */
public class Customer {
private Long cust_id;
private String cust_name;
//private String cust_source;
//private String cust_industry;
//private String cust_level;
private String cust_phone;
private String cust_mobile;
private String cust_image;
public String getCust_image() {
	return cust_image;
}
public void setCust_image(String cust_image) {
	this.cust_image = cust_image;
}
/*
 * �ͻ��ֵ���һ
 */
private BaseDict baseDictSource;
private BaseDict baseDictIndustry;

private BaseDict baseDictLevel;

/*
 * �ͻ���ϵ�� һ�Զ�
 */
private Set<LinkMan> linkMans=new HashSet<LinkMan>();


public Set<LinkMan> getLinkMans() {
	return linkMans;
}
public void setLinkMans(Set<LinkMan> linkMans) {
	this.linkMans = linkMans;
}
public BaseDict getBaseDictSource() {
	return baseDictSource;
}
public void setBaseDictSource(BaseDict baseDictSource) {
	this.baseDictSource = baseDictSource;
}
public BaseDict getBaseDictIndustry() {
	return baseDictIndustry;
}
public void setBaseDictIndustry(BaseDict baseDictIndustry) {
	this.baseDictIndustry = baseDictIndustry;
}
public BaseDict getBaseDictLevel() {
	return baseDictLevel;
}
public void setBaseDictLevel(BaseDict baseDictLevel) {
	this.baseDictLevel = baseDictLevel;
}
public Long getCust_id() {
	return cust_id;
}
public void setCust_id(Long cust_id) {
	this.cust_id = cust_id;
}
public String getCust_name() {
	return cust_name;
}
public void setCust_name(String cust_name) {
	this.cust_name = cust_name;
}

public String getCust_phone() {
	return cust_phone;
}
public void setCust_phone(String cust_phone) {
	this.cust_phone = cust_phone;
}
public String getCust_mobile() {
	return cust_mobile;
}
public void setCust_mobile(String cust_mobile) {
	this.cust_mobile = cust_mobile;
}


}
