package com.gzf.sypt.POJO.DTO;

/**
 * @author guozifan
 * @Description: 手机验证码注册传输对象
 * @date 2018/8/3 14:58
 */
public class PhoneVerificRegisterInputDTO {
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
