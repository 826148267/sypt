package com.gzf.sypt.POJO.enums;

public enum LoginTypeEnum {
    BY_PHONE_PASSWORD(0,"手机密码登录"),BY_PHONE_VERIFIC(1,"手机验证码登录");

    //用户登录状态状态码
    private Integer status;
    //用户登录状态状态信息
    private String statusInfo;

    /**
     * 用户登录类型状态构造器
     * @param status    用户登录类型状态码
     * @param statusInfo    用户登录类型状态信息
     */
    private LoginTypeEnum(Integer status, String statusInfo) {
        this.status = status;
        this.statusInfo = statusInfo;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }
}
