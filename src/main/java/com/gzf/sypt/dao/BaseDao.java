package com.gzf.sypt.dao;


import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface BaseDao<T, PK> {
    /**
     * 插入操作
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 删除操作
     * @param id
     * @return
     */
    int delete(PK id);

    /**
     * 更改某项信息
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * 获取单个对象操作
     * @param id
     * @return
     */
    T getOne(PK id);

    /**
     * 获取所有对象操作
     * @param id
     * @return
     */
    List<T> listAll(PK id);

    /**
     * 查询所有对象
     * @return
     */
    List<T> listAll();
}
