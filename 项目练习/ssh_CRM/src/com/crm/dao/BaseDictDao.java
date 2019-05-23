package com.crm.dao;

import java.util.List;

import com.crm.domain.BaseDict;

public interface BaseDictDao {

	public List<BaseDict> findByTypeCode(String dict_type_code);

}
