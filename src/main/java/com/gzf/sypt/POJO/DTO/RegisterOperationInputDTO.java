package com.gzf.sypt.POJO.DTO;

import java.io.Serializable;

/**
 * @author guozifan
 * @Description: 注册业务数据传入对象
 * @date 2018/7/31 17:01
 */
public class RegisterOperationInputDTO<T> implements Serializable {
    private Integer registerType;   // 注册类型
    private T vo;   // vo对象，在适配器中转过来

    public RegisterOperationInputDTO(){}

    public T getVo() {
        return vo;
    }

    public void setVo(T vo) {
        this.vo = vo;
    }

    public Integer getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
    }
}
