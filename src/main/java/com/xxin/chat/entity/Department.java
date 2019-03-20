package com.xxin.chat.entity;

import javax.persistence.*;

@Entity
public class Department {

    @Id
    @Column(length = 3)
    private String departmentCode;

    @ManyToOne
    @JoinColumn(name = "society_code", nullable = false)
    private Society society;

    private String departmentName;

//    @Column(nullable = false)
//    private Integer roundState;

    @Column(length = 19, nullable = false)
    private String createAt;

    @Column(length = 19, nullable = false)
    private String updateAt;

    public Department() {
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Society getSociety() {
        return society;
    }

    public void setSociety(Society society) {
        this.society = society;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

//    public Integer getRoundState() {
//        return roundState;
//    }
//
//    public void setRoundState(Integer roundState) {
//        this.roundState = roundState;
//    }
}
