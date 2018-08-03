package com.gzf.sypt.POJO.entity;

/**
 * @author guozifan
 * @Description: 手机验证码注册实体对象
 * @date 2018/8/3 15:13
 */
public class PhoneVerificRegister {
    private Long phoneNum;  // 手机号码
    private String verific; // 验证码

    public Long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getVerific() {
        return verific;
    }

    public void setVerific(String verific) {
        this.verific = verific;
    }
}
