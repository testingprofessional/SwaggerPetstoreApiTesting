package com.freenow.blog.models;

public enum Status {

    available("available"),
    pending("pending"),
    sold("sold");

    public String value;

    Status(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
