package com.rtmap.traffic.bcm.domain;

import java.util.Date;

public class SysUser {
    private String userCd;

    private String password;

    private String realName;

    private Boolean sex;

    private Date birthday;

    private String mobileFirst;

    private String mobileSecond;

    private String officePhone;

    private String homePhone;

    private String email;

    private Date latestLoginDate;

    private Date createDate;

    private String createUser;

    private Date modifiedDate;

    private String modifiedUser;

    public String getUserCd() {
        return userCd;
    }

    public void setUserCd(String userCd) {
        this.userCd = userCd == null ? null : userCd.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobileFirst() {
        return mobileFirst;
    }

    public void setMobileFirst(String mobileFirst) {
        this.mobileFirst = mobileFirst == null ? null : mobileFirst.trim();
    }

    public String getMobileSecond() {
        return mobileSecond;
    }

    public void setMobileSecond(String mobileSecond) {
        this.mobileSecond = mobileSecond == null ? null : mobileSecond.trim();
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone == null ? null : officePhone.trim();
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone == null ? null : homePhone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getLatestLoginDate() {
        return latestLoginDate;
    }

    public void setLatestLoginDate(Date latestLoginDate) {
        this.latestLoginDate = latestLoginDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser == null ? null : modifiedUser.trim();
    }
}