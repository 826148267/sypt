package com.gzf.sypt.POJO.DTO;

/**
 * @author guozifan
 * @Description: 邮箱验证码注册传输对象
 * @date 2018/8/3 15:01
 */
public class QQMailVerificRegisterInputDTO {
    private String mailBox;
    private String verific;

    public String getMailBox() {
        return mailBox;
    }

    public void setMailBox(String mailBox) {
        this.mailBox = mailBox;
    }

    public String getVerific() {
        return verific;
    }

    public void setVerific(String verific) {
        this.verific = verific;
    }
}
