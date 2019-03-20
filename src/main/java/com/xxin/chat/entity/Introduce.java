package com.xxin.chat.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Introduce implements Serializable {
    @Id
    private int id;
    private String name;
    private String introduce;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}
