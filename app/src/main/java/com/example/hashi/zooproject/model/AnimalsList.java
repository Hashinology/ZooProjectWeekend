package com.example.hashi.zooproject.model;

public class AnimalsList {
    private String id, name, category, detail;

    public AnimalsList(String id, String name, String category, String detail) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
