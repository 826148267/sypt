package com.gzf.sypt.POJO.DO;

import com.alibaba.fastjson.JSONObject;
import com.gzf.sypt.util.SMSUtil;
import com.gzf.sypt.util.SecurityCodeUtil;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author guozifan
 * @Description: 手机验证码注册领域对象
 * @date 2018/8/2 15:29
 */
public class RegisterPhoneVerificDO {
    private Integer id;
    private Long phoneNum;
    private String verific;
    private Date createTime;
    private Date endTime;

    public JSONObject sendVerificCode() throws IOException {
        return SMSUtil.sendVerificCode(String.valueOf(phoneNum), verific);
    }

    public Map initParams(){
        // 存放验证码和加密后需要持久化到数据库的密文
        Map map = SecurityCodeUtil.getVerific(phoneNum.toString());
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
