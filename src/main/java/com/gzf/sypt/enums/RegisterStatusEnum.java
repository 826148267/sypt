package com.gzf.sypt.enums;

public enum RegisterStatusEnum {
    REQUEST_VERIFIC_FAIL(0,"拉取验证码失败"),REQUEST_VERIFIC_SUCCESS(1,"拉取验证码成功"),
    BAD_VERIFIC(2,"验证码错误"),RIGHT_VERIFIC(3,"验证码正确"),
    INSERT_USER_AUTH_FAIL(4, "保存注册者信息失败"),INSERT_USER_AUTH_SUCCESS(5, "保存注册者信息成功"),
    UNKNOWN_REGISTER_TYPE(6, "未知注册类型"),SEND_VERIFIC_SUCCESS(7, "验证码已发送"),
    SEND_VERIFIC_FAIL(8, "验证码发送失败");

    //注册操作状态码
    private Integer status;
    //注册操作状态信息
    private String statusInfo;

    /**
     * 注册操作状态构造器
     * @param status    注册操作状态码
     * @param statusInfo    注册操作状态信息
     */
    private RegisterStatusEnum(Integer status, String statusInfo){
        this.status = status;
        this.statusInfo = statusInfo;
    }

    public Integer getStatus() {
        return status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }
}
