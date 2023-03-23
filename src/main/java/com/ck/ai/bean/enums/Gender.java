package com.ck.ai.bean.enums;

public enum Gender {
    MALE("男"),
    FEMALE("女"),
    UNKNOWN("未知");

    private String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
