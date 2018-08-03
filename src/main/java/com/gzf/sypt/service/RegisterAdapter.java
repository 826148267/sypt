package com.gzf.sypt.service;

/**
 * @author guozifan
 * @Description: 注册功能适配器接口
 * @date 2018/7/27 12:56
 */
public interface RegisterAdapter<T, S> extends RegisterService<T, S> {
    /**
     * 根据不同的注册方式进行注入不同是注册实现类
     * @param integer 注册类型
     */
   void setAnywayRegister(Integer integer);
}
