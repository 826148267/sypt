package com.gzf.sypt.service;

import java.io.IOException;

/**
 * @author guozifan
 * @Description: 通过验证码注册功能接口
 * @date 2018/7/25 18:23
 */
public interface RegisterByVerific<T, S> extends AnywayRegister {
    /**
     * 申请验证码操作
     * @param S 传入注册验证对象
     * @return
     */
    T requestVerificCode(S S) throws IOException;

    /**
     * 检查验证码操作
     * @param S 传入注册验证对象
     * @return
     */
    T checkVerificCode(S S);
}
