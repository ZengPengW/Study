package com.zp.oauth2.resource.service;

import java.util.List;

import com.zp.oauth2.resource.pojo.TbContent;

public interface TbContentService {

    /**
     * 根据 ID 获取
     *
     * @param id ID
     * @return {@link TbContent}
     */
    public TbContent getById(Long id) ;

    /**
     * 获取全部数据
     *
     * @return {@link List<TbContent>}
     */
    public List<TbContent> selectAll() ;

    /**
     * 新增
     *
     * @param tbContent {@link TbContent}
     * @return int 数据库受影响行数
     */
    public int insert(TbContent tbContent) ;

    /**
     * 编辑
     *
     * @param tbContent {@link TbContent}
     * @return int 数据库受影响行数
     */
    public int update(TbContent tbContent);

    /**
     * 删除
     *
     * @param id ID
     * @return int 数据库受影响行数
     */
    public int delete(Long id);

}
