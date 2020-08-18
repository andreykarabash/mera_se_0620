package com.mera.lesson7;

public enum AppleColors {

    GREEN("зеленое"),
    RED("красное"),
    YELLOW("желтое");

    private String color;

    AppleColors(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
