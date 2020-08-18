package src.lesson6;

import java.util.Objects;

public class Date {
    private int year;
    private String month;
    private int day;

    public Date(int year, String month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "Date{" +
                "year=" + year +
                ", month='" + month + '\'' +
                ", day=" + day +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return year == date.year &&
                day == date.day &&
                month.equals(date.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }
}

class monthParameters {
    enum months {
        January(31),
        February(28),
        March(31),
        April(30),
        May(31),
        June(30),
        July(31),
        August(31),
        September(30),
        October(31),
        November(30),
        December(31);

        private int value;

        months(int value) {
            this.value = value;

        }

        public int getValue() {
            return this.value;
        }
    }
}
