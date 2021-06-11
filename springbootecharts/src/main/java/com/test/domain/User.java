package com.test.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Date birthday;
    private int age;
    private double salary;
    private Timestamp regTime;

    public User() {
    }

    public User(String name, Date birthday, int age, double salary, Timestamp regTime) {
        this.name = name;
        this.birthday = birthday;
        this.age = age;
        this.salary = salary;
        this.regTime = regTime;
    }

    public User(Integer id, String name, Date birthday, int age, double salary, Timestamp regTime) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.age = age;
        this.salary = salary;
        this.regTime = regTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Timestamp getRegTime() {
        return regTime;
    }

    public void setRegTime(Timestamp regTime) {
        this.regTime = regTime;
    }
}
