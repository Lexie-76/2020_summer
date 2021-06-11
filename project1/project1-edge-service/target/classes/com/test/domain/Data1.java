package com.test.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Data1 implements Serializable {
    private List<String> categories = new ArrayList<>();
    private List<Integer> data = new ArrayList<>();

    public Data1() {
    }

    public Data1(List<String> categories, List<Integer> data) {
        this.categories = categories;
        this.data = data;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}

