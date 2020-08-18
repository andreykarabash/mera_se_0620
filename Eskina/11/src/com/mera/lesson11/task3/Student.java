package com.mera.lesson11.task3;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Student {
    private String name;
    private Set<Lection> personalSchedule = new HashSet<>();

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Lection> getSchedule() {
        return personalSchedule;
    }

    public void setSchedule(Set<Lection> schedule) {
        this.personalSchedule = schedule;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", personalSchedule=" + personalSchedule +
                '}' + "\n";
    }

    public void addLectionToSchedule(Lection lection) {
        personalSchedule.add(lection);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(personalSchedule, student.personalSchedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, personalSchedule);
    }

    public void fillPersonalSchedule(Set<Lection> generalSchedule) {
        //lets fill the personal schedule with items from general schedule
        if (generalSchedule == null) {
            return;
        }

        int maxNum = generalSchedule.size();
        int min = 10;
        int numLections = 0;
        if (min >= maxNum ) {
            numLections = ThreadLocalRandom.current().nextInt(maxNum);
        } else {
            numLections = ThreadLocalRandom.current().nextInt(min, maxNum+1);
        }

        while (personalSchedule.size() != numLections) {
            addLectionToSchedule(chooseRandomLection(generalSchedule));
        }
    }

    public Lection chooseRandomLection(Set<Lection> schedule) {
        Lection[] array = schedule.toArray(new Lection[schedule.size()]);
        return array[ThreadLocalRandom.current().nextInt(array.length)];
    }
}
