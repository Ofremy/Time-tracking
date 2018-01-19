package com.buselorest.domain;

import com.buselorest.model.dao.interfaces.Identified;

public class Activity implements Identified<Integer> {
    private Integer id;
    private String name;

    public Activity() {
    }

    public Activity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
