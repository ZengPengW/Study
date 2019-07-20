package com.zp.springcloud.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zp.springcloud.entities.Dept;

import feign.hystrix.FallbackFactory;
/**
 * 熔断
 * @author zp
 *
 */
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {

	//创造熔断
	@Override
	public DeptClientService create(Throwable cause) {
		// TODO Auto-generated method stub
		return new DeptClientService() {
			
			@Override
			public List<Dept> list() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Dept get(Long id) {
				return new Dept().setDname("该id:"+id+"不存在").setDb_source("not find database");

			}
			
			@Override
			public boolean add(Dept dept) {
				// TODO Auto-generated method stub
				return false;
			}
		};
	}

}
