package com.gzf.sypt.service;

import java.io.IOException;
public interface RegisterService<T, S> {
    /**
     * 进行账号注册
     * @param s 注册业务传入对象
     * @return t 验证码
     * @throws IOException
     */
    T register(S s) throws IOException;
}
