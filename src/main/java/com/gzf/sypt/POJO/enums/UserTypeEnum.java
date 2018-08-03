package com.gzf.sypt.POJO.enums;

public enum UserTypeEnum {
    CONSIGNOR(0,"委托人"),CONSIGNEE(1,"受托人"),SUPPER_ADAMIN(2,"超级管理员");

    //用户身份类型类型码
    private Integer status;
    //用户身份类型类型码
    private String statusInfo;

    /**
     * 用户身份类型构造器
     * @param status    用户身份类型类型码
     * @param statusInfo    用户身份类型类型码
     */
    private UserTypeEnum(Integer status, String statusInfo){
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
