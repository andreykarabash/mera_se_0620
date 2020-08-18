package lectionanalisys;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Student {
    private String name;
    private Set<Lection> attendedLections = new HashSet<>();

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Set<Lection> attendedLection) {
        this.name = name;
        this.attendedLections = attendedLection;
    }

    public String getName() {
        return name;
    }

    public Set<Lection> getAttendedLections() {
        return attendedLections;
    }

    public void addAttendedLection(Lection attendedLection) {
        attendedLections.add(attendedLection);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
