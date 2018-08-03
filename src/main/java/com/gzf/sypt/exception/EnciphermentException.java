package com.gzf.sypt.exception;

/**
 * @author guozifan
 * @Description: 对加密错误进行封装
 * @date 2018/6/5 17:41
 */
public class EnciphermentException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EnciphermentException(String message) {
        super(message);
    }
}
