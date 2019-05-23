package com.crm.service;

import java.util.List;

import com.crm.domain.BaseDict;

public interface BaseDictService {

	public List<BaseDict> findByTypeCode(String dict_type_code);

}
