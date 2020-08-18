package com.mera.lesson6;

public enum Months {

    JANUARY("Январь", 31),
    FEBRUARY("Февраль", 28),
    MARCH("Март", 31),
    APRIL("Апрель",30),
    MAY("Май",31),
    JUNE("Июнь",30),
    JULY("Июль",31),
    AUGUST("Август",31),
    SEPTEMBER("Сентябрь",30),
    OCTOBER("Октябрь",31),
    NOVEMBER("Ноябрь",30),
    DECEMBER("Декабрь",31);

    private String name;
    private int maxDay;

    Months(String name, int maxDay) {
        this.name = name;
        this.maxDay = maxDay;
    }

    public String getName() {
        return name;
    }

    public int getMaxDay() {
        return maxDay;
    }
}
