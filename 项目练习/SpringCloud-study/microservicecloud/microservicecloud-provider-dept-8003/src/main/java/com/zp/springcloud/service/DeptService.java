package com.zp.springcloud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zp.springcloud.dao.DeptDao;
import com.zp.springcloud.entities.Dept;


public interface DeptService {

	
	
	public boolean add(Dept dept);

	public Dept get(Long id);

	public List<Dept> list();
	
}
