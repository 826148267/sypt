package com.gzf.sypt.POJO.DO;

import com.gzf.sypt.util.QQMailUtil;
import com.gzf.sypt.util.SecurityCodeUtil;

import java.util.Date;
import java.util.Map;

/**
 * @author guozifan
 * @Description: QQ邮箱验证码注册领域对象
 * @date 2018/8/2 17:55
 */
public class RegisterQQMailVerificDO {
    private Integer id;
    private String mailBox;
    private String verific;
    private Date createTime;
    private Date endTime;

    /**
     * 发送QQ邮箱验证码
     * @return
     */
    public boolean sendVerificCode(){
        return QQMailUtil.sendVerificCode(mailBox, verific);
    }

    /**
     * 初始化验证码，以密文方式存入
     * 创建时间，失效时间
     */
    public Map initParams(){
        // 存放验证码和加密后需要持久化到数据库的密文
        Map map = SecurityCodeUtil.getVerific(mailBox);
        // 将验证码摘要密文设置到领域对象中
        verific = map.get("cipher").toString();
        // 获取当前时间
        long currentTime = System.currentTimeMillis();
        // 格式化当前时间和失效时间
        Date createTime = new Date(currentTime);
        Date endTime = new Date(currentTime + (5 * 60 * 1000));
        // 验证码申请时间和失效时间
        this.createTime = createTime;
        this.endTime = endTime;
        return map;
    }

    public void decodeVerific(Map map){
        verific = map.get("key").toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
