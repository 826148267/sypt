package com.gzf.sypt.POJO.enums;

public enum OnlineStatusEnum {
    ONLINE(0,"在线"),OFFLINE(1,"离线"),FROZEN(2,"被冻结");

    //用户在线状态状态码
    private Integer status;
    //用户在线状态状态信息
    private String statusInfo;

    /**
     * 用户在线状态状态构造器
     * @param status    用户在线状态状态码
     * @param statusInfo    用户在线状态状态码
     */
    private OnlineStatusEnum(Integer status,String statusInfo){
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
