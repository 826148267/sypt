package com.gzf.sypt.POJO.DTO;

import com.gzf.sypt.enums.RegisterStatusEnum;

import java.io.Serializable;

/**
 * @author guozifan
 * @Description: 注册操作用于返回结果的传输对象
 * @date 2018/8/2 18:17
 */
public class RegisterOperationOutputDTO<T> implements Serializable {
    private Integer status; // 操作状态码
    private String statusInfo;  // 操作状态信息
    private T entity;   // 实体类

    public RegisterOperationOutputDTO(RegisterStatusEnum registerStatusEnum){
        this.status = registerStatusEnum.getStatus();
        this.statusInfo = registerStatusEnum.getStatusInfo();
    }

    public RegisterOperationOutputDTO(RegisterStatusEnum registerStatusEnum, T entity){
        this.status = registerStatusEnum.getStatus();
        this.statusInfo = registerStatusEnum.getStatusInfo();
        this.entity = entity;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }
}
