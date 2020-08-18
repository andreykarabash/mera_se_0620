package com.mera.task6.date;

import com.mera.task5.ui.UIElement;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Date {

    private final int day;
    private final Month month;
    private final int year;

    public Date(int day, Month month, int year) {
        this.month = month;
        this.year = year;
        this.day = Math.min(day, month.getDays());
    }

    public int getDay() {
        return day;
    }

    public Month getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public static Date randomDateAtYear(int year) {
        int index = ThreadLocalRandom.current().nextInt(Month.values().length);
        Month month = Month.values()[index];
        int day = ThreadLocalRandom.current().nextInt(month.getDays() + 1);
        return new Date(day, month, year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return day == date.day &&
                year == date.year &&
                month == date.month;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    @Override
    public String toString() {
        return "Date{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
