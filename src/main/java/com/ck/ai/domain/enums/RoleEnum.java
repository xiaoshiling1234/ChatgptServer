package com.ck.ai.domain.enums;

public enum RoleEnum {
    TEMP("TEMP",-1),
    WEEKLY("Weekly", 7), // 周卡，有效期 7 天
    MONTHLY("Monthly", 30), // 月卡，有效期 30 天
    YEARLY("Yearly", 365), // 年卡，有效期 365 天
    PERMANENT("Permanent", -1); // 永久卡，永久有效

    private final String name; // 卡片名称
    private final int days; // 有效天数

    RoleEnum(String name, int days) {
        this.name = name;
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public int getDays() {
        return days;
    }
}
