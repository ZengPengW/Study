package com.taotao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.taotao.mapper.TestMapper;
import com.taotao.service.TestService;
@Service
@Transactional
public class TestServiceImpl implements TestService {

	@Autowired
	private TestMapper mapper;
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String queryNow() {
		
		return mapper.queryNow();
	}

}
