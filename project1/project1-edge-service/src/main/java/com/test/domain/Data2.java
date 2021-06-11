package com.test.domain;

import java.io.Serializable;

public class Data2 implements Serializable {
    private String category;
    private Integer num;

    public Data2() {
    }

    public Data2(String category, Integer num) {
        this.category = category;
        this.num = num;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
