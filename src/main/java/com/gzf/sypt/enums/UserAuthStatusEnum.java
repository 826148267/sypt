package com.gzf.sypt.enums;

/**
 * @author guozifan
 * @Description:
 * @date 2018/6/8 15:56
 */
public enum UserAuthStatusEnum {
    INSRET_FAIL(0, "保存登陆者信息失败"),INSERT_SUCCESS(1, "保存登陆者信息成功");
    // 登录授权表操作状态码
    private Integer status;
    // 登录授权表操作状态信息
    private String statusInfo;

    /**
     * 初始化状态码和状态信息
     * @param status
     * @param statusInfo
     */
    private UserAuthStatusEnum(Integer status, String statusInfo){
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
