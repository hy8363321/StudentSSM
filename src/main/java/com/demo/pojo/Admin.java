package com.demo.pojo;

import java.sql.Date;

public class Admin {
    private Long id;

    private String username;

    private String password;

    private String adminName;

    private String adminNumber;

    private Long adminTelephone;

    private Integer adminSys;

    private Date enterTime;

    private String safetyProblem;

    private String safetyAnswer;

    private Long approverNumbers;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminNumber() {
        return adminNumber;
    }

    public void setAdminNumber(String adminNumber) {
        this.adminNumber = adminNumber == null ? null : adminNumber.trim();
    }

    public Long getAdminTelephone() {
        return adminTelephone;
    }

    public void setAdminTelephone(Long adminTelephone) {
        this.adminTelephone = adminTelephone;
    }

    public Integer getAdminSys() {
        return adminSys;
    }

    public void setAdminSys(Integer adminSys) {
        this.adminSys = adminSys;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public String getSafetyProblem() {
        return safetyProblem;
    }

    public void setSafetyProblem(String safetyProblem) {
        this.safetyProblem = safetyProblem == null ? null : safetyProblem.trim();
    }

    public String getSafetyAnswer() {
        return safetyAnswer;
    }

    public void setSafetyAnswer(String safetyAnswer) {
        this.safetyAnswer = safetyAnswer == null ? null : safetyAnswer.trim();
    }

    public Long getApproverNumbers() {
        return approverNumbers;
    }

    public void setApproverNumbers(Long approverNumbers) {
        this.approverNumbers = approverNumbers;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}