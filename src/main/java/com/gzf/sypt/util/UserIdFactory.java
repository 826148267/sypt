package com.gzf.sypt.util;

import java.net.UnknownHostException;

/**
 * @author guozifan
 * @Description: 创建网站唯一Id的静态工厂类
 * @date 2018/7/28 17:17
 */
public class UserIdFactory {
    public static Long getUserId() throws UnknownHostException {
        return new UserIdBuilder().createUserId();
    }
}
