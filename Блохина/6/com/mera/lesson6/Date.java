package com.mera.lesson6;

public class Date {
    int day;
    Months month;
    int year;

    public Date(int day, Months month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public Months getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
