package com.gzf.sypt.POJO.PO;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class UserAuthPO implements Serializable {
    private Integer id;
    private Long userId; //用户在网站中的唯一标识
    private Integer userType; //登陆者身份
    private Integer loginType; //登录类型
    private String loginName; //登录时的账号
    private String certificate; //登录时的密码凭证
    private Date createTime; //创建时间
    private Date lastLoginTime; //最后一次登录的时间
    private String lastLoginIp; //最后一次登录的IP
    private String lastLoginProvince; //最后一次登录的省份
    private String lastLoginCity;   //最后一次登录的城市
    private Integer onlineStatus;    //当前在线状态,表示不在线，1表示在线，2表示被冻结

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getLastLoginProvince() {
        return lastLoginProvince;
    }

    public void setLastLoginProvince(String lastLoginProvince) {
        this.lastLoginProvince = lastLoginProvince;
    }

    public String getLastLoginCity() {
        return lastLoginCity;
    }

    public void setLastLoginCity(String lastLoginCity) {
        this.lastLoginCity = lastLoginCity;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    @Override
    public String toString() {
        return "UserAuth{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userType=" + userType +
                ", loginType=" + loginType +
                ", loginName='" + loginName + '\'' +
                ", certificate='" + certificate + '\'' +
                ", createTime=" + createTime +
                ", lastLoginTime=" + lastLoginTime +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", lastLoginProvince='" + lastLoginProvince + '\'' +
                ", lastLoginCity='" + lastLoginCity + '\'' +
                ", onlineStatus=" + onlineStatus +
                '}';
    }
}
