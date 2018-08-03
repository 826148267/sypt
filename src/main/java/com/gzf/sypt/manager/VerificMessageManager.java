package com.gzf.sypt.manager;

import java.io.IOException;

/**
 * @author guozifan
 * @Description: 使用第三方接口进行验证信息操作
 * @date 2018/7/28 18:28
 */
public interface VerificMessageManager<T, S> {
    /**
     * 向用户发送验证码
     * @param s 验证领域对象
     * @return
     */
    T sendVerificCode(S s) throws IOException;
}
