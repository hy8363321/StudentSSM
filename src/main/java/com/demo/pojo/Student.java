package com.demo.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Student {
    private Long id;

    private Long studentNumber;

    private String studentPassword;

    private String studentName;

    private Integer studentSex;

    private String studentNumberid;

    private String studentSchool;

    private String studentDepartment;

    private String studentSpecialty;

    private Long studentTelephone;

    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date studentEnterTime;

    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date studentGraduateTime;

    private Integer studentGraduateStatus;

    private Integer studentApplicationStatus;
/*
    public Student(Long studentNumber, String studentPassword, String studentName, Integer studentSex, String studentNumberid, String studentSchool, String studentDepartment, String studentSpecialty, Long studentTelephone, Date studentEnterTime, Integer studentGraduateStatus, Integer studentApplicationStatus) {
        this.studentNumber = studentNumber;
        this.studentPassword = studentPassword;
        this.studentName = studentName;
        this.studentSex = studentSex;
        this.studentNumberid = studentNumberid;
        this.studentSchool = studentSchool;
        this.studentDepartment = studentDepartment;
        this.studentSpecialty = studentSpecialty;
        this.studentTelephone = studentTelephone;
        this.studentEnterTime = studentEnterTime;
        this.studentGraduateStatus = studentGraduateStatus;
        this.studentApplicationStatus = studentApplicationStatus;
    }

    public Student() {
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Long studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword == null ? null : studentPassword.trim();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public Integer getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(Integer studentSex) {
        this.studentSex = studentSex;
    }

    public String getStudentNumberid() {
        return studentNumberid;
    }

    public void setStudentNumberid(String studentNumberid) {
        this.studentNumberid = studentNumberid == null ? null : studentNumberid.trim();
    }

    public String getStudentSchool() {
        return studentSchool;
    }

    public void setStudentSchool(String studentSchool) {
        this.studentSchool = studentSchool == null ? null : studentSchool.trim();
    }

    public String getStudentDepartment() {
        return studentDepartment;
    }

    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment = studentDepartment == null ? null : studentDepartment.trim();
    }

    public String getStudentSpecialty() {
        return studentSpecialty;
    }

    public void setStudentSpecialty(String studentSpecialty) {
        this.studentSpecialty = studentSpecialty == null ? null : studentSpecialty.trim();
    }

    public Long getStudentTelephone() {
        return studentTelephone;
    }

    public void setStudentTelephone(Long studentTelephone) {
        this.studentTelephone = studentTelephone;
    }

    public Date getStudentEnterTime() {
        return studentEnterTime;
    }

    public void setStudentEnterTime(Date studentEnterTime) {
        this.studentEnterTime = studentEnterTime;
    }

    public Date getStudentGraduateTime() {
        return studentGraduateTime;
    }

    public void setStudentGraduateTime(Date studentGraduateTime) {
        this.studentGraduateTime = studentGraduateTime;
    }

    public Integer getStudentGraduateStatus() {
        return studentGraduateStatus;
    }

    public void setStudentGraduateStatus(Integer studentGraduateStatus) {
        this.studentGraduateStatus = studentGraduateStatus;
    }

    public Integer getStudentApplicationStatus() {
        return studentApplicationStatus;
    }

    public void setStudentApplicationStatus(Integer studentApplicationStatus) {
        this.studentApplicationStatus = studentApplicationStatus;
    }
}