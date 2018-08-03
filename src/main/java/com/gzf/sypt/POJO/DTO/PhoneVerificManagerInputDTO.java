package com.gzf.sypt.POJO.DTO;

import java.io.Serializable;

/**
 * @author guozifan
 * @Description: 手机验证码注册三方接口传输对象
 * @date 2018/7/31 21:52
 */
public class PhoneVerificManagerInputDTO implements Serializable {
    private String verific;
    private Long phoneNum;

    public String getVerific() {
        return verific;
    }

    public void setVerific(String verific) {
        this.verific = verific;
    }

    public Long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }
}
