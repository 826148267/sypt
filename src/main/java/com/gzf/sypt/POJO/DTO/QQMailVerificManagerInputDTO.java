package com.gzf.sypt.POJO.DTO;

import java.io.Serializable;

/**
 * @author guozifan
 * @Description: 邮箱验证码注册manager层传输对象
 * @date 2018/8/2 18:11
 */
public class QQMailVerificManagerInputDTO implements Serializable {
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
