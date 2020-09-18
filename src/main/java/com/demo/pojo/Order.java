package com.demo.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Order {
    private Long id;

    private Long approverNumber;

    private String clubName;

    private Integer clubScale;

    private String college;

    private String area;

    private String clubTypes;

    private String clubRegistrant;

    private String clubPrincipal;

    private String clubAdviser;

    private Long registrantTelephone;

    private Long principalTelephone;

    private Long studentId;

    private Long principalNumber;

    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date registrantTime;

    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date applicationTime;

    private Integer documentStatus;

    private Integer registerStatus;

/*------------------------------------------------------------------------------------------------------------*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApproverNumber() {
        return approverNumber;
    }

    public void setApproverNumber(Long approverNumber) {
        this.approverNumber = approverNumber;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName == null ? null : clubName.trim();
    }

    public Integer getClubScale() {
        return clubScale;
    }

    public void setClubScale(Integer clubScale) {
        this.clubScale = clubScale;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college == null ? null : college.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getClubTypes() {
        return clubTypes;
    }

    public void setClubTypes(String clubTypes) {
        this.clubTypes = clubTypes == null ? null : clubTypes.trim();
    }

    public String getClubRegistrant() {
        return clubRegistrant;
    }

    public void setClubRegistrant(String clubRegistrant) {
        this.clubRegistrant = clubRegistrant == null ? null : clubRegistrant.trim();
    }

    public String getClubPrincipal() {
        return clubPrincipal;
    }

    public void setClubPrincipal(String clubPrincipal) {
        this.clubPrincipal = clubPrincipal == null ? null : clubPrincipal.trim();
    }

    public String getClubAdviser() {
        return clubAdviser;
    }

    public void setClubAdviser(String clubAdviser) {
        this.clubAdviser = clubAdviser == null ? null : clubAdviser.trim();
    }

    public Long getRegistrantTelephone() {
        return registrantTelephone;
    }

    public void setRegistrantTelephone(Long registrantTelephone) {
        this.registrantTelephone = registrantTelephone;
    }

    public Long getPrincipalTelephone() {
        return principalTelephone;
    }

    public void setPrincipalTelephone(Long principalTelephone) {
        this.principalTelephone = principalTelephone;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getPrincipalNumber() {
        return principalNumber;
    }

    public void setPrincipalNumber(Long principalNumber) {
        this.principalNumber = principalNumber;
    }

    public Date getRegistrantTime() {
        return registrantTime;
    }

    public void setRegistrantTime(Date registrantTime) {
        this.registrantTime = registrantTime;
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public Integer getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(Integer documentStatus) {
        this.documentStatus = documentStatus;
    }

    public Integer getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(Integer registerStatus) {
        this.registerStatus = registerStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", approverNumber=" + approverNumber +
                ", clubName='" + clubName + '\'' +
                ", clubScale=" + clubScale +
                ", college='" + college + '\'' +
                ", area='" + area + '\'' +
                ", clubTypes='" + clubTypes + '\'' +
                ", clubRegistrant='" + clubRegistrant + '\'' +
                ", clubPrincipal='" + clubPrincipal + '\'' +
                ", clubAdviser='" + clubAdviser + '\'' +
                ", registrantTelephone=" + registrantTelephone +
                ", principalTelephone=" + principalTelephone +
                ", studentId=" + studentId +
                ", principalNumber=" + principalNumber +
                ", registrantTime=" + registrantTime +
                ", applicationTime=" + applicationTime +
                ", documentStatus=" + documentStatus +
                ", registerStatus=" + registerStatus +
                '}';
    }
}