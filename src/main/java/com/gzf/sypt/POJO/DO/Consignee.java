package com.gzf.sypt.POJO.DO;

public class Consignee extends User {
    private Integer id;
    private String userId;
    private String userName;
    private Integer userSex;
    private String province;
    private String city;
    private Long phone;
    private String mailBox;
    private Long qq;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getMailBox() {
        return mailBox;
    }

    public void setMailBox(String mailBox) {
        this.mailBox = mailBox;
    }

    public Long getQq() {
        return qq;
    }

    public void setQq(Long qq) {
        this.qq = qq;
    }

    @Override
    public String toString() {
        return "Consignee{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userSex=" + userSex +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", phone=" + phone +
                ", mailBox='" + mailBox + '\'' +
                ", qq=" + qq +
                '}';
    }
}
