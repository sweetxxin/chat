package com.xxin.chat.entity;

import javax.persistence.*;

@Entity
public class EnrollTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "open_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "department_code", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "society_code", nullable = false)
    private Society society;

    @Column(length = 140)
    private String Description;

//    @Column(nullable = false)
//    private Integer round;

    @Column(length = 19, nullable = false)
    private String createAt;

    @Column(length = 19, nullable = false)
    private String updateAt;

    public EnrollTable() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Society getSociety() {
        return society;
    }

    public void setSociety(Society society) {
        this.society = society;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

//    public Integer getRound() {
//        return round;
//    }
//
//    public void setRound(Integer round) {
//        this.round = round;
//    }

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
}
