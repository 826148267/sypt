package com.gzf.sypt.POJO.enums;

/**
 * @author guozifan
 * @Description: ${todo}
 * @date 2018/6/7 0:39
 */
public enum RegisterTypeEnum {
    BY_VERIFIC(0, "通过验证码"),
    BY_PASSWORD(1, "通过密码"),
    BY_AUTH(2, "通过第三方授权"),
    REQUEST_VERIFIC_PHONE(4, "拉取手机验证码"),
    REQUEST_VERIFIC_QQMAIL(5, "拉取邮箱验证码"),
    CHECK_VERIFIC_PHONE(6, "检验手机验证码是否正确"),
    CHECK_VERIFIC_QQMAIL(7, "检验邮箱验证码是否正确");

    private Integer status;
    private String statusInfo;

    /**
     * 构造注册类型和注册类型信息
     * @param status
     * @param statusInfo
     */
    private RegisterTypeEnum(Integer status, String statusInfo) {
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
