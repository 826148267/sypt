package com.gzf.sypt.POJO.PO;

import java.io.Serializable;
import java.util.Date;

/**
 * @author guozifan
 * @Description: 验证操作业务领域对象
 * @date 2018/7/28 23:00
 */
public abstract class AbstractVerific implements Serializable {
    private Integer id;
    private Long phoneNum; //手机号码
    private String mailBox; //邮箱
    private String verific;  //验证码
    private Date createTime;    //验证码创建时间
    private Date endTime;   //验证码失效时间
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
