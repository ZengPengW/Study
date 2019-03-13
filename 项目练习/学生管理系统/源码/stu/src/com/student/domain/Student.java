package com.student.domain;

import java.util.Date;

//学生对象封装的bean
public class Student {
private int sid;
private String sname;
private String gender;
private String phone;
private Date birthday;
private String hobby;
private String info;
public Student(String sname, String gender, String phone, Date birthday, String hobby, String info) {
	this.gender=gender;
	this.hobby=hobby;
	this.info=info;
	this.phone=phone;
	this.sname=sname;
	this.birthday=birthday;
}
public Student() {
	
}
public Student(int sid, String sname, String gender, String phone, Date birthday, String hobby, String info) {
	this.gender=gender;
	this.hobby=hobby;
	this.info=info;
	this.phone=phone;
	this.sname=sname;
	this.birthday=birthday;
	this.sid=sid;
}
public int getSid() {
	return sid;
}
public void setSid(int sid) {
	this.sid = sid;
}
public String getSname() {
	return sname;
}
public void setSname(String sname) {
	this.sname = sname;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}
public String getHobby() {
	return hobby;
}
public void setHobby(String hobby) {
	this.hobby = hobby;
}
public String getInfo() {
	return info;
}
public void setInfo(String info) {
	this.info = info;
}

}
