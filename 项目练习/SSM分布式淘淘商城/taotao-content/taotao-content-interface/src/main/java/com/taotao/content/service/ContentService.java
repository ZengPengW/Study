package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
 public EasyUIDataGridResult<TbContent> getContentList(Long categoryId,Integer page,Integer rows);

public TaotaoResult saveContent(TbContent tContent);

public TaotaoResult updateContent(TbContent tContent);

public TaotaoResult deleteContent(String ids);

public List<TbContent> getContentListByCatId(Long categoryId);
}
