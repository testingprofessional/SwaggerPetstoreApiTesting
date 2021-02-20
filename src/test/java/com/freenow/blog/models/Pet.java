package com.freenow.blog.models;

public class Pet {
    private String name;
    private String status;
    private int id;

    public Pet(String status, String name, int id) {
        this.status = status;
        this.name = name;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
